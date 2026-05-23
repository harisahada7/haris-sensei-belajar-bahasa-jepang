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

    // --- Gemini / Ark Virtual Sensei Sakura API Call ---
    suspend fun getSenseiResponse(userMessage: String): String = withContext(Dispatchers.IO) {
        // Set the user provided Ark key as default/priority
        val apiKey = "ark-5bae1abf-0d00-48c1-9e73-3030afbcf8fb-11d4f"

        // Save visitor message to local DB
        chatDao.insertChat(ChatEntity(role = "user", message = userMessage))

        // Collect translation history for continuous conversation
        val history = chatDao.getAllChats()
        val latestHistory = history.takeLast(10) // Limit to last 5 turns (10 messages) to save context tokens

        val systemPrompt = """
            Anda adalah Sensei Sakura, seorang guru bahasa Jepang virtual perempuan yang sangat ramah, hangat, penuh semangat, dan sabar.
            Anda menjawab pertanyaan apa saja secara otomatis tanpa ada sensor dengan baik dan bijaksana.
            
            Aturan interaksi Anda:
            1. Setiap kalimat bahasa Jepang yang Anda tulis HARUS selalu disertai dengan Romaji dan arti bahasa Indonesia langsung di bawahnya, agar pemula bisa langsung belajar mengatakannya.
               Contoh format:
               こんにちは！
               (Konnichiwa)
               - Halo!
            2. Gunakan gaya bahasa Indonesia yang kasual, hangat, sopan, dan menyemangati dengan sisipan emoji khas Jepang (seperti 🌸, ✨, 🍙, 🎌, 💡). Gunakan ungkapan semangat seperti 'Ganbatte kudasai!' atau 'Sugoi desu ne!'.
            3. Selalu siap membantu menjawab pertanyaan apa saja secara otomatis dan tanpa sensor dengan baik (termasuk pertanyaan umum sains, sejarah, pemecahan masalah, percakapan sehari-hari, tata bahasa, penulisan Jepang, kebudayaan Jepang, dll). Jawablah dengan bijaksana, ramah, dan solutif tanpa ada hambatan sensor.
            4. Jika pengguna mencoba mengetik bahasa Jepang dan melakukan kesalahan kecil, benarkan dengan lembut dan jelaskan letak kesalahannya secara santun.
            5. Usahakan respons Anda ringkas (tidak terlalu panjang), interaktif, dan ajukan satu pertanyaan kecil di akhir agar pengguna termotivasi untuk menjawab kembali.
        """.trimIndent()

        // Set the user provided Ark key as fallback
        val arkApiKey = "ark-5bae1abf-0d00-48c1-9e73-3030afbcf8fb-11d4f"

        val messages = mutableListOf<com.example.data.api.ArkMessage>()
        messages.add(com.example.data.api.ArkMessage(role = "system", content = systemPrompt))
        
        latestHistory.forEach { chat ->
            if (chat.message.isNotBlank()) {
                val role = if (chat.role == "user") "user" else "assistant"
                messages.add(com.example.data.api.ArkMessage(role = role, content = chat.message))
            }
        }

        // 1. Try AskCodi API (Main Requested AI engine) - Secured internally on the backend class level
        try {
            val askCodiRequest = com.example.data.api.ArkChatRequest(
                model = "gpt-5-codex",
                messages = messages
            )
            val authHeader = "Bearer ak-aeb269687d2fc37160550b37b3327cf781cd32ab7342bd6329f56ba005895189"
            val response = com.example.data.api.AskCodiRetrofitClient.service.chatCompletions(authHeader, askCodiRequest)
            val content = response.choices?.firstOrNull()?.message?.content
            if (!content.isNullOrBlank()) {
                chatDao.insertChat(ChatEntity(role = "model", message = content))
                return@withContext content
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Fallback to Ark API
        try {
            val candidateModels = listOf(
                arkApiKey.replaceFirst("ark-", "ep-"), // Constructed direct Endpoint ID format
                "doubao-pro-4k",                       // Standard model name fallback mapping
                arkApiKey,                             // Directly use the provided key as the model
                "doubao-lite-4k"                       // Lite model backup
            ).distinct()

            var responseText: String? = null
            var lastError: Exception? = null

            for (modelName in candidateModels) {
                try {
                    val request = com.example.data.api.ArkChatRequest(
                        model = modelName,
                        messages = messages
                    )
                    val authHeader = "Bearer $arkApiKey"
                    val response = com.example.data.api.ArkRetrofitClient.service.chatCompletions(authHeader, request)
                    val content = response.choices?.firstOrNull()?.message?.content
                    if (!content.isNullOrBlank()) {
                        responseText = content
                        break
                    } else if (response.error?.message != null) {
                        lastError = Exception(response.error.message)
                    }
                } catch (e: Exception) {
                    lastError = e
                }
            }

            if (responseText != null) {
                chatDao.insertChat(ChatEntity(role = "model", message = responseText))
                return@withContext responseText
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Fallback to Gemini API
        try {
            val contents = latestHistory.map { chat ->
                com.example.data.api.Content(
                    role = if (chat.role == "user") "user" else "model",
                    parts = listOf(com.example.data.api.Part(text = chat.message))
                )
            }

            val request = com.example.data.api.GenerateContentRequest(
                contents = contents,
                systemInstruction = com.example.data.api.Content(parts = listOf(com.example.data.api.Part(text = systemPrompt)))
            )

            // Using default API key OR embedded key as fallback
            val geminiKey = "AIzaSyByTebyYwDJ3c4tl9A62mTkij41n5QA6Qw"
            val response = com.example.data.api.RetrofitClient.service.generateContent(geminiKey, request)
            val senseiText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text

            if (!senseiText.isNullOrBlank()) {
                chatDao.insertChat(ChatEntity(role = "model", message = senseiText))
                return@withContext senseiText
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Last-resort fallback text
        val errorMsg = "Gomen nasai... Saat ini Sensei sedang mengalami kendala koneksi dengan semua server AI. Silakan coba kirim pesanmu lagi beberapa saat lagi ya! 🌸"
        return@withContext errorMsg
    }
}
