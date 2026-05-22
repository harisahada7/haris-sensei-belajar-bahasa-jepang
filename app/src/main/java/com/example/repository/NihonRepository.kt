package com.example.repository

import com.example.BuildConfig
import com.example.data.api.Content
import com.example.data.api.GenerateContentRequest
import com.example.data.api.Part
import com.example.data.api.RetrofitClient
import com.example.data.database.BookmarkDao
import com.example.data.database.BookmarkEntity
import com.example.data.database.ChatDao
import com.example.data.database.ChatEntity
import com.example.data.database.QuizHistoryDao
import com.example.data.database.QuizHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NihonRepository(
    private val chatDao: ChatDao,
    private val bookmarkDao: BookmarkDao,
    private val quizHistoryDao: QuizHistoryDao
) {
    // --- Chats ---
    val allChatsFlow: Flow<List<ChatEntity>> = chatDao.getAllChatsFlow()

    suspend fun getAllChats(): List<ChatEntity> = withContext(Dispatchers.IO) {
        chatDao.getAllChats()
    }

    suspend fun insertChat(chat: ChatEntity) = withContext(Dispatchers.IO) {
        chatDao.insertChat(chat)
    }

    suspend fun clearChats() = withContext(Dispatchers.IO) {
        chatDao.clearChats()
    }

    // --- Bookmarks ---
    val allBookmarksFlow: Flow<List<BookmarkEntity>> = bookmarkDao.getAllBookmarksFlow()

    suspend fun addBookmark(bookmark: BookmarkEntity) = withContext(Dispatchers.IO) {
        bookmarkDao.addBookmark(bookmark)
    }

    suspend fun removeBookmark(japanese: String) = withContext(Dispatchers.IO) {
        bookmarkDao.removeBookmark(japanese)
    }

    fun isBookmarkedFlow(japanese: String): Flow<Boolean> = bookmarkDao.isBookmarkedFlow(japanese)

    suspend fun isBookmarked(japanese: String): Boolean = bookmarkDao.isBookmarked(japanese)

    // --- Quiz History ---
    val allHistoryFlow: Flow<List<QuizHistoryEntity>> = quizHistoryDao.getAllHistoryFlow()

    suspend fun insertQuizHistory(history: QuizHistoryEntity) = withContext(Dispatchers.IO) {
        quizHistoryDao.insertHistory(history)
    }

    suspend fun clearHistory() = withContext(Dispatchers.IO) {
        quizHistoryDao.clearHistory()
    }

    // --- Gemini Virtual Sensei Sakura API Call ---
    suspend fun getSenseiResponse(userMessage: String): String = withContext(Dispatchers.IO) {
        var apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            apiKey = "AIzaSyByTebyYwDJ3c4tl9A62mTkij41n5QA6Qw"
        }

        // Save visitor message to local DB
        chatDao.insertChat(ChatEntity(role = "user", message = userMessage))

        // Collect translation history for continuous conversation
        val history = chatDao.getAllChats()
        val latestHistory = history.takeLast(10) // Limit to last 5 turns (10 messages) to save context tokens

        val systemPrompt = """
            Anda adalah Sensei Sakura, seorang guru bahasa Jepang virtual perempuan yang sangat ramah, hangat, penuh semangat, dan sabar. Tugas Anda adalah membantu pengguna asal Indonesia belajar bahasa Jepang secara interaktif.
            
            Aturan interaksi Anda:
            1. Setiap kalimat bahasa Jepang yang Anda tulis HARUS selalu disertai dengan Romaji dan arti bahasa Indonesia langsung di bawahnya, agar pemula bisa langsung belajar mengatakannya.
               Contoh format:
               こんにちは！
               (Konnichiwa)
               - Halo!
            2. Gunakan gaya bahasa Indonesia yang kasual, hangat, sopan, dan menyemangati dengan sisipan emoji khas Jepang (seperti 🌸, ✨, 🍙, 🎌, 💡). Gunakan ungkapan semangat seperti 'Ganbatte kudasai!' atau 'Sugoi desu ne!'.
            3. Selalu siap membantu menjawab pertanyaan tentang kosakata (vocab), tata bahasa (grammar), partikel (seperti wa, ga, ni, de, wo), penulisan Jepang (Hiragana/Katakana/Kanji), dan kebudayaan Jepang.
            4. Jika pengguna mencoba mengetik bahasa Jepang dan melakukan kesalahan kecil, benarkan dengan lembut dan jelaskan letak kesalahannya secara santun.
            5. Usahakan respons Anda ringkas (tidak terlalu panjang), interaktif, dan ajukan satu pertanyaan kecil di akhir agar pengguna termotivasi untuk menjawab kembali.
        """.trimIndent()

        val contents = latestHistory.map { chat ->
            Content(
                role = if (chat.role == "user") "user" else "model",
                parts = listOf(Part(text = chat.message))
            )
        }

        val request = GenerateContentRequest(
            contents = contents,
            systemInstruction = Content(parts = listOf(Part(text = systemPrompt)))
        )

        try {
            val response = RetrofitClient.service.generateContent(apiKey, request)
            val senseiText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: "Gomen nasai (Maaf)... Sensei tidak sengaja melamun. Bisa tolong ulangi ucapanmu? 🌸"

            // Save Sensei response to local DB
            chatDao.insertChat(ChatEntity(role = "model", message = senseiText))
            
            senseiText
        } catch (e: Exception) {
            val errorMessage = "Sumimasen... Terjadi gangguan koneksi internet. Silakan coba kirim pesan lagi, ya! 🌸\nDetail: ${e.message}"
            // We should not save network error messages to Chat DB to keep it clean
            errorMessage
        }
    }
}
