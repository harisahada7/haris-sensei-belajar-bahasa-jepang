package com.example

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.database.AppDatabase
import com.example.repository.NihonRepository
import com.example.ui.screens.NihonMainScreen
import com.example.ui.viewmodel.NihonViewModel
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_nihonMainScreen_renders_successfully() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        
        // Build an in-memory SQLite database for robust, hermetic unit testing
        val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val repository = NihonRepository(
            chatDao = database.chatDao(),
            bookmarkDao = database.bookmarkDao(),
            quizHistoryDao = database.quizHistoryDao()
        )
        val viewModel = NihonViewModel(context as Application, repository)
        
        composeTestRule.setContent {
            NihonMainScreen(viewModel = viewModel)
        }
        
        composeTestRule.waitForIdle()
        assertNotNull(viewModel)
        database.close()
    }
}
