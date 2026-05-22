package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.BookmarkEntity
import com.example.data.database.ChatEntity
import com.example.data.database.QuizHistoryEntity
import com.example.data.model.VocabularyItem
import com.example.repository.NihonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

data class QuizQuestion(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

data class QuizState(
    val categoryName: String = "",
    val questions: List<QuizQuestion> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val score: Int = 0,
    val isQuizFinished: Boolean = false
)

class NihonViewModel(
    application: Application,
    private val repository: NihonRepository
) : AndroidViewModel(application) {

    private var tts: TextToSpeech? = null
    private val _isTtsReady = MutableStateFlow(false)
    val isTtsReady = _isTtsReady.asStateFlow()

    // --- Database States ---
    val allChats: StateFlow<List<ChatEntity>> = repository.allChatsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allBookmarks: StateFlow<List<BookmarkEntity>> = repository.allBookmarksFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuizHistory: StateFlow<List<QuizHistoryEntity>> = repository.allHistoryFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Chat Input & AI State ---
    private val _isAnalyzingSensei = MutableStateFlow(false)
    val isAnalyzingSensei = _isAnalyzingSensei.asStateFlow()

    // --- Quiz States ---
    private val _quizState = MutableStateFlow<QuizState?>(null)
    val quizState: StateFlow<QuizState?> = _quizState.asStateFlow()

    init {
        try {
            // Initialize Android Text-to-Speech specifically set for Japanese language
            tts = TextToSpeech(application) { status ->
                try {
                    if (status == TextToSpeech.SUCCESS) {
                        val result = tts?.setLanguage(Locale.JAPAN)
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            // Fallback so speech utility attempts are never completely blocked
                            tts?.setLanguage(Locale.getDefault())
                        }
                        _isTtsReady.value = true
                    }
                } catch (ce: Exception) {
                    ce.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _isTtsReady.value = false
        }
        
        // Populate first dialogue greeting if chat is empty on first startup
        viewModelScope.launch {
            try {
                val list = repository.getAllChats()
                if (list.isEmpty()) {
                    repository.insertChat(
                        ChatEntity(
                            role = "model",
                            message = "Konnichiwa! 🌸 Selamat datang di NihonSensei.\n" +
                                    "Nama saya Sensei Sakura, guru bahasa Jepang virtualmu. " +
                                    "Di sini kamu bisa bertanya seputar penulisan Hiragana, Katakana, Kanji, tata bahasa (grammar), percakapan, atau budaya Jepang.\n\n" +
                                    "Yuk mulai obrolan! Apa yang ingin kamu pelajari hari ini? ✨"
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // --- Chat Logic ---
    fun sendMessageToSensei(message: String) {
        if (message.isBlank()) return
        
        viewModelScope.launch {
            _isAnalyzingSensei.value = true
            repository.getSenseiResponse(message)
            _isAnalyzingSensei.value = false
        }
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            repository.clearChats()
            // Re-insert initial welcome message
            repository.insertChat(
                ChatEntity(
                    role = "model",
                    message = "Konnichiwa! 🌸 Selamat datang kembali.\nAda yang perlu Sensei bantu hari ini? 🎌"
                )
            )
        }
    }

    // --- Pronunciation Speech Utility ---
    fun speakJapanese(rawText: String) {
        if (!_isTtsReady.value) return
        
        try {
            // Clean Japanese text: extract only Kanji/Kana symbols (strip bracket notes like "一 (いち)" or Romaji labels)
            val cleanText = rawText.split(" ").first().substringBefore("(").trim()
            if (cleanText.isNotEmpty()) {
                tts?.speak(cleanText, TextToSpeech.QUEUE_FLUSH, null, "nihon_tts_id")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // --- Bookmarks Logic ---
    fun toggleBookmark(item: VocabularyItem, category: String, isAlreadyBookmarked: Boolean) {
        viewModelScope.launch {
            if (isAlreadyBookmarked) {
                repository.removeBookmark(item.japanese)
            } else {
                repository.addBookmark(
                    BookmarkEntity(
                        japanese = item.japanese,
                        romaji = item.romaji,
                        meaning = item.meaning,
                        category = category
                    )
                )
            }
        }
    }

    fun isBookmarkedFlow(japanese: String): Flow<Boolean> {
        return repository.isBookmarkedFlow(japanese)
    }

    // --- Quiz Engine Logic ---
    fun startQuiz(categoryName: String, items: List<VocabularyItem>) {
        if (items.isEmpty()) return
        
        val quizQuestions = mutableListOf<QuizQuestion>()
        
        items.forEach { item ->
            // Variation 1: Kanji/Kana -> Indonesian Meaning
            run {
                val questionText = "Apa arti dari kosakata berikut?\n\n🎌  ${item.japanese}  (${item.romaji})"
                val correctAnswer = item.meaning
                val otherItems = items.filter { it.japanese != item.japanese }
                val incorrectOptions = otherItems.shuffled().take(3).map { it.meaning }.toMutableList()
                
                while (incorrectOptions.size < 3) {
                    incorrectOptions.add("Lainnya ${incorrectOptions.size + 1}")
                }
                
                val options = (incorrectOptions + correctAnswer).shuffled()
                val correctIdx = options.indexOf(correctAnswer)
                
                quizQuestions.add(
                    QuizQuestion(
                        questionText = questionText,
                        options = options,
                        correctAnswerIndex = correctIdx,
                        explanation = "${item.japanese} (${item.romaji}) berarti \"${item.meaning}\".\nTahu tidak? ${item.notes}"
                    )
                )
            }
            
            // Variation 2: Romaji -> Indonesian Meaning
            run {
                val questionText = "Apa arti dari ucapan Romaji berikut?\n\n🔊  \"${item.romaji}\""
                val correctAnswer = item.meaning
                val otherItems = items.filter { it.romaji != item.romaji }
                val incorrectOptions = otherItems.shuffled().take(3).map { it.meaning }.toMutableList()
                
                while (incorrectOptions.size < 3) {
                    incorrectOptions.add("Lainnya ${incorrectOptions.size + 1}")
                }
                
                val options = (incorrectOptions + correctAnswer).shuffled()
                val correctIdx = options.indexOf(correctAnswer)
                
                quizQuestions.add(
                    QuizQuestion(
                        questionText = questionText,
                        options = options,
                        correctAnswerIndex = correctIdx,
                        explanation = "Kata Romaji \"${item.romaji}\" ditulis dalam bahasa Jepang sebagai ${item.japanese} yang bermakna \"${item.meaning}\"."
                    )
                )
            }
            
            // Variation 3: Meaning -> Kanji/Kana (with Romaji)
            run {
                val questionText = "Bagaimana menulis atau mengucapkan kata ini dalam Bahasa Jepang?\n\n💡  \"${item.meaning}\""
                val correctAnswer = "${item.japanese} (${item.romaji})"
                val otherItems = items.filter { it.meaning != item.meaning }
                val incorrectOptions = otherItems.shuffled().take(3).map { "${it.japanese} (${it.romaji})" }.toMutableList()
                
                while (incorrectOptions.size < 3) {
                    incorrectOptions.add("Lainnya ${incorrectOptions.size + 1}")
                }
                
                val options = (incorrectOptions + correctAnswer).shuffled()
                val correctIdx = options.indexOf(correctAnswer)
                
                quizQuestions.add(
                    QuizQuestion(
                        questionText = questionText,
                        options = options,
                        correctAnswerIndex = correctIdx,
                        explanation = "\"${item.meaning}\" ditulis sebagai ${item.japanese} (${item.romaji}).\nInfo: ${item.notes}"
                    )
                )
            }
            
            // Variation 4: Meaning -> Japanese Characters (isolasi)
            run {
                val questionText = "Temukan tulisan Jepang (Kanji/Kana) yang tepat untuk makna berikut:\n\n📖  \"${item.meaning}\""
                val correctAnswer = item.japanese
                val otherItems = items.filter { it.japanese != item.japanese }
                val incorrectOptions = otherItems.shuffled().take(3).map { it.japanese }.toMutableList()
                
                while (incorrectOptions.size < 3) {
                    incorrectOptions.add("Lainnya ${incorrectOptions.size + 1}")
                }
                
                val options = (incorrectOptions + correctAnswer).shuffled()
                val correctIdx = options.indexOf(correctAnswer)
                
                quizQuestions.add(
                    QuizQuestion(
                        questionText = questionText,
                        options = options,
                        correctAnswerIndex = correctIdx,
                        explanation = "\"${item.meaning}\" dalam penulisan bahasa Jepang adalah ${item.japanese} (${item.romaji})."
                    )
                )
            }
        }
        
        _quizState.value = QuizState(
            categoryName = categoryName,
            questions = quizQuestions.shuffled().take(25), // Pick exactly 25 random questions
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            isAnswerSubmitted = false,
            score = 0,
            isQuizFinished = false
        )
    }

    fun selectAnswer(optionIndex: Int) {
        val current = _quizState.value ?: return
        if (current.isAnswerSubmitted) return
        _quizState.value = current.copy(selectedAnswerIndex = optionIndex)
    }

    fun submitAnswer() {
        val current = _quizState.value ?: return
        if (current.selectedAnswerIndex == null || current.isAnswerSubmitted) return
        
        val question = current.questions[current.currentQuestionIndex]
        val isCorrect = current.selectedAnswerIndex == question.correctAnswerIndex
        val newScore = if (isCorrect) current.score + 1 else current.score

        _quizState.value = current.copy(
            isAnswerSubmitted = true,
            score = newScore
        )
    }

    fun nextQuestion() {
        val current = _quizState.value ?: return
        if (!current.isAnswerSubmitted) return
        
        val nextIndex = current.currentQuestionIndex + 1
        val isFinished = nextIndex >= current.questions.size

        if (isFinished) {
            // Save quiz stats to persistent database
            val percentage = if (current.questions.isNotEmpty()) (current.score * 100) / current.questions.size else 0
            viewModelScope.launch {
                repository.insertQuizHistory(
                    QuizHistoryEntity(
                        categoryName = current.categoryName,
                        score = current.score,
                        totalQuestions = current.questions.size,
                        percentage = percentage
                    )
                )
            }
            _quizState.value = current.copy(isQuizFinished = true)
        } else {
            _quizState.value = current.copy(
                currentQuestionIndex = nextIndex,
                selectedAnswerIndex = null,
                isAnswerSubmitted = false
            )
        }
    }

    fun exitQuiz() {
        _quizState.value = null
    }

    fun clearQuizHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
