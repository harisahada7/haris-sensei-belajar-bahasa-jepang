package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.data.database.AppDatabase
import com.example.repository.NihonRepository
import com.example.ui.screens.NihonMainScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.NihonViewModel
import com.example.ui.viewmodel.NihonViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Initialize local persistent Room Database (singleton)
        val database = AppDatabase.getInstance(applicationContext)
        
        // 2. Initialize application repository binding Room DAOs and REST AI features
        val repository = NihonRepository(
            chatDao = database.chatDao(),
            bookmarkDao = database.bookmarkDao(),
            quizHistoryDao = database.quizHistoryDao()
        )
        
        // 3. Initialize interactive ViewModel through constructor factory
        val factory = NihonViewModelFactory(application, repository)
        val viewModel: NihonViewModel by viewModels { factory }

        // 4. Force edge to edge full bleed status/navigation padding
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                NihonMainScreen(viewModel = viewModel)
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun Greeting(name: String, modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier) {
    androidx.compose.material3.Text(text = "Hello $name!", modifier = modifier)
}
