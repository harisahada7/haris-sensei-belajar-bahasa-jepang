package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.BookmarkEntity
import com.example.data.database.ChatEntity
import com.example.data.model.TopicCategory
import com.example.data.model.VocabularyData
import com.example.data.model.VocabularyItem
import com.example.ui.viewmodel.NihonViewModel
import kotlinx.coroutines.launch

// Sakura Cherry Blossom Color Theme
val SakuraLightPink = Color(0xFFFFF0F2)
val SakuraPink = Color(0xFFFFB7C5)
val SakuraAccent = Color(0xFFFF5277)
val DeepZenNavy = Color(0xFF1E1E2E)
val LightZenGray = Color(0xFFF6F6F9)
val HinomaruRed = Color(0xFFBC002D)
val CorrectGreen = Color(0xFF4CAF50)
val IncorrectOrange = Color(0xFFFF5722)

enum class Screen(val title: String, val icon: ImageVector, val tag: String) {
    BELAJAR("Materi", Icons.Default.MenuBook, "nav_belajar"),
    SENSEI("Sensei AI", Icons.Default.Chat, "nav_sensei"),
    KUIS("Latihan Kuis", Icons.Default.Quiz, "nav_kuis"),
    BOOKMARK("Kamus Saya", Icons.Default.Bookmark, "nav_bookmark")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NihonMainScreen(viewModel: NihonViewModel) {
    var currentScreen by remember { mutableStateOf(Screen.BELAJAR) }
    
    // Track selected category on the Learn screen dynamically
    var selectedCategory by remember { mutableStateOf<TopicCategory?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = R.drawable.img_japanese_teacher_banner_1779432393217,
                            contentDescription = "Haris Ahada Logo",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(DeepZenNavy),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Haris Ahada",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                style = TextStyle(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.White, SakuraPink)
                                    ),
                                    shadow = Shadow(
                                        color = Color(0xB3000000),
                                        offset = Offset(2f, 3f),
                                        blurRadius = 4f
                                    )
                                )
                            )
                            Text(
                                "Guru Jepang Virtual 3D",
                                fontSize = 11.sp,
                                color = SakuraLightPink
                            )
                        }
                    }
                },
                navigationIcon = {
                    // Show Back Arrow if we are inside a specific category view
                    if (currentScreen == Screen.BELAJAR && selectedCategory != null) {
                        IconButton(
                            onClick = { selectedCategory = null },
                            modifier = Modifier.testTag("app_bar_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Kembali ke kategori",
                                tint = Color.White
                            )
                        }
                    }
                },
                actions = {
                    if (currentScreen == Screen.SENSEI) {
                        IconButton(
                            onClick = { viewModel.clearChatHistory() },
                            modifier = Modifier.testTag("clear_chat_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Hapus Riwayat Chat",
                                tint = SakuraPink
                            )
                        }
                    } else if (currentScreen == Screen.KUIS) {
                        IconButton(
                            onClick = { viewModel.clearQuizHistory() },
                            modifier = Modifier.testTag("clear_quiz_history_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Hapus Nilai Kuis",
                                tint = SakuraPink
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepZenNavy,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = DeepZenNavy,
                windowInsets = WindowInsets.navigationBars,
            ) {
                Screen.values().forEach { screen ->
                    NavigationBarItem(
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                fontSize = 11.sp,
                                fontWeight = if (currentScreen == screen) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = DeepZenNavy,
                            selectedTextColor = SakuraPink,
                            indicatorColor = SakuraPink,
                            unselectedIconColor = SakuraPink.copy(alpha = 0.6f),
                            unselectedTextColor = SakuraPink.copy(alpha = 0.6f)
                        ),
                        modifier = Modifier.testTag(screen.tag)
                    )
                }
            }
        },
        containerColor = LightZenGray
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (currentScreen) {
                Screen.BELAJAR -> BelajarScreen(
                    viewModel = viewModel,
                    selectedCategory = selectedCategory,
                    onCategorySelect = { selectedCategory = it },
                    onGoToQuiz = { category ->
                        viewModel.startQuiz(category.name, category.items)
                        currentScreen = Screen.KUIS
                    }
                )
                Screen.SENSEI -> SenseiScreen(viewModel = viewModel)
                Screen.KUIS -> QuizScreen(
                    viewModel = viewModel,
                    onGoToLearn = {
                        selectedCategory = null
                        currentScreen = Screen.BELAJAR
                    }
                )
                Screen.BOOKMARK -> BookmarkScreen(viewModel = viewModel)
            }
        }
    }
}

// ============================================
// SCREEN 1: BELAJAR (VOCAB & CATEGORIES)
// ============================================
@Composable
fun BelajarScreen(
    viewModel: NihonViewModel,
    selectedCategory: TopicCategory?,
    onCategorySelect: (TopicCategory?) -> Unit,
    onGoToQuiz: (TopicCategory) -> Unit
) {
    if (selectedCategory == null) {
        // Show all Category grids
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = R.drawable.img_japanese_teacher_banner_1779432393217,
                            contentDescription = "Cover Haris Ahada Jepang",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "日本へようこそ！ 🌸",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepZenNavy
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                "Selamat datang di Haris Ahada! Pilih salah satu topik pelajaran di bawah untuk mempelajari kosakata dasar sehari-hari lengkap dengan Romaji dan arti bahasa Indonesianya.",
                                fontSize = 13.sp,
                                color = Color.DarkGray,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Pilihan Kategori Belajar:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DeepZenNavy
                )
            }

            items(VocabularyData.categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategorySelect(category) }
                )
            }
        }
    } else {
        // Show all list vocabulary inside a category
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onCategorySelect(null) },
                        modifier = Modifier
                            .background(SakuraLightPink, CircleShape)
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali ke kategori",
                            tint = DeepZenNavy,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(
                        text = selectedCategory.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepZenNavy,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                    )
                    OutlinedButton(
                        onClick = { onGoToQuiz(selectedCategory) },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = HinomaruRed),
                        modifier = Modifier.testTag("start_quiz_from_category_button")
                    ) {
                        Text("Kuis", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                Text(
                    text = selectedCategory.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                )
            }

            items(selectedCategory.items) { item ->
                val bookmarks by viewModel.allBookmarks.collectAsState()
                val isBookmarked = bookmarks.any { it.japanese == item.japanese }

                VocabItemCard(
                    item = item,
                    categoryName = selectedCategory.name,
                    isBookmarked = isBookmarked,
                    onSpeak = { viewModel.speakJapanese(item.japanese) },
                    onBookmarkToggle = {
                        viewModel.toggleBookmark(item, selectedCategory.name, isBookmarked)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onGoToQuiz(selectedCategory) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("category_bottom_start_quiz_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = DeepZenNavy)
                ) {
                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = null,
                        tint = SakuraPink
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Uji Pemahaman: Mulai Kuis!", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: TopicCategory,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("category_card_${category.name.replace(" ", "_")}"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SakuraLightPink),
                contentAlignment = Alignment.Center
            ) {
                val emoji = when (category.iconName) {
                    "waving_hand" -> "👋"
                    "numbers" -> "🔢"
                    "restaurant" -> "🍣"
                    "chat" -> "💬"
                    "flight_takeoff" -> "🚅"
                    "directions" -> "🧭"
                    "family" -> "👪"
                    "health" -> "❤️"
                    "weather" -> "☀️"
                    "shopping" -> "🛍️"
                    "school" -> "🏫"
                    "sports_esports" -> "🎮"
                    "nature" -> "🌲"
                    "work" -> "💼"
                    "palette" -> "🎨"
                    "commute" -> "🚊"
                    "home" -> "🏠"
                    else -> "🎌"
                }
                Text(emoji, fontSize = 26.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DeepZenNavy
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = category.description,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun VocabItemCard(
    item: VocabularyItem,
    categoryName: String,
    isBookmarked: Boolean,
    onSpeak: () -> Unit,
    onBookmarkToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSpeak() }
                ) {
                    Text(
                        text = item.japanese,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = HinomaruRed,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "(${item.romaji})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Row {
                    IconButton(
                        onClick = onSpeak,
                        modifier = Modifier
                            .background(SakuraLightPink, CircleShape)
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Dengarkan pengucapan",
                            tint = DeepZenNavy,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier
                            .background(SakuraLightPink, CircleShape)
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Star else Icons.Default.BookmarkBorder,
                            contentDescription = "Simpan bookmark",
                            tint = if (isBookmarked) SakuraAccent else Color.DarkGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.meaning,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DeepZenNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.notes,
                fontSize = 12.sp,
                color = Color.DarkGray,
                lineHeight = 16.sp
            )
        }
    }
}

// ============================================
// SCREEN 2: GURU VIRTUAL (AI SENSEI SAKURA CHAT)
// ============================================
@Composable
fun SenseiScreen(viewModel: NihonViewModel) {
    val chats by viewModel.allChats.collectAsState()
    val isAnalyzing by viewModel.isAnalyzingSensei.collectAsState()
    var inputMessage by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current

    // Auto-scroll chat to latest message
    LaunchedEffect(chats.size, isAnalyzing) {
        if (chats.isNotEmpty()) {
            listState.animateScrollToItem(chats.size - 1)
        }
    }

    val quickQuestions = listOf(
        "Tanya Grammar Partikel 🌸",
        "Aturan Penulisan Jepang 🎌",
        "Percakapan di Restoran 🍣",
        "Koreksi kalimat saya: 'Watashi no namae desu haris' 🧠"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightZenGray)
    ) {
        // Sensei Avatar Info Panel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepZenNavy)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(SakuraPink),
                contentAlignment = Alignment.Center
            ) {
                Text("🌸", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Sensei Sakura",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color.Green)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Guru Jepang Virtual AI (Online)",
                        fontSize = 11.sp,
                        color = SakuraPink
                    )
                }
            }
        }

        // Chat Bubble list
        Box(modifier = Modifier.weight(1f)) {
            if (chats.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Memuat obrolan dengan Sensei Sakura...",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(chats) { chat ->
                        ChatBubble(chat = chat, onVoice = { viewModel.speakJapanese(chat.message) })
                    }
                    
                    if (isAnalyzing) {
                        item {
                            TypingBubble()
                        }
                    }
                }
            }
        }

        // Quick Suggestion Chips
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickQuestions) { question ->
                SuggestionChip(
                    onClick = {
                        val finalPrompt = when (question) {
                            "Tanya Grammar Partikel 🌸" -> "Sensei Sakura, tolong ajarkan dan beri contoh simpel tentang perbedaan partikel wa (は) dan ga (が)?"
                            "Aturan Penulisan Jepang 🎌" -> "Sensei, jelaskan apa perbedaan kegunaan huruf Hiragana, Katakana, dan Kanji dan kapan kita menggunakannya?"
                            "Percakapan di Restoran 🍣" -> "Sensei, ayo kita simulasi latihan percakapan memesan sushi di restoran Jepang. Mulai obrolan menggunakannya ya!"
                            else -> "Sensei, tolong koreksi tata bahasa kalimat Jepang saya yang satu ini dan jelaskan salahnya di mana: 'Watashi no namae desu haris.'"
                        }
                        viewModel.sendMessageToSensei(finalPrompt)
                    },
                    label = { Text(question, fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = SakuraLightPink,
                        labelColor = DeepZenNavy
                    )
                )
            }
        }

        // Chat Input Panel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputMessage,
                onValueChange = { inputMessage = it },
                placeholder = { Text("Tanya Sensei Sakura tentang Jepang...", fontSize = 13.sp) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("chat_input_field"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SakuraAccent,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = LightZenGray,
                    unfocusedContainerColor = LightZenGray
                ),
                shape = RoundedCornerShape(24.dp),
                maxLines = 3
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (inputMessage.isNotBlank()) {
                        viewModel.sendMessageToSensei(inputMessage)
                        inputMessage = ""
                        focusManager.clearFocus()
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(DeepZenNavy)
                    .testTag("send_message_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Kirim pesan",
                    tint = SakuraPink,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ChatBubble(chat: ChatEntity, onVoice: () -> Unit) {
    val isUser = chat.role == "user"
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isUser) {
            // Sensei mini face portrait
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(SakuraPink)
                    .align(Alignment.Bottom),
                contentAlignment = Alignment.Center
            ) {
                Text("🌸", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        )
                    )
                    .background(if (isUser) SakuraPink else Color.White)
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = chat.message,
                        fontSize = 14.sp,
                        color = DeepZenNavy,
                        lineHeight = 20.sp
                    )

                    // Display a quick Japanese Voice playback hint for model chats
                    if (!isUser) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable { onVoice() }
                                .padding(vertical = 4.dp, horizontal = 6.dp)
                                .background(SakuraLightPink, RoundedCornerShape(4.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Dengarkan Pengucapan Jepang",
                                tint = DeepZenNavy,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Dengar Suara", fontSize = 10.sp, color = DeepZenNavy, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TypingBubble() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(SakuraPink),
            contentAlignment = Alignment.Center
        ) {
            Text("🌸", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(12.dp)
        ) {
            Text(
                "Sensei Sakura sedang mengetik... ✍️",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

// ============================================
// SCREEN 3: LATIHAN KUIS (QUIZ ENGINE)
// ============================================
@Composable
fun QuizScreen(viewModel: NihonViewModel, onGoToLearn: () -> Unit) {
    val quizState by viewModel.quizState.collectAsState()
    val history by viewModel.allQuizHistory.collectAsState()
    var showCertificate by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (quizState == null) {
        // Show Welcome / Quizzes Hub
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DeepZenNavy),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Kuis Evaluasi Jepang 🎌",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = SakuraPink
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Uji pemahaman kosakata, Romaji, dan terjemahan bahasa Jepangmu.",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center
                        )
                        
                        // Show simple cumulative stats
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "${history.size}",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text("Selesai", fontSize = 11.sp, color = SakuraPink)
                            }
                            
                            val bestScore = history.maxOfOrNull { it.percentage } ?: 0
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "$bestScore%",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text("Skor Terbaik", fontSize = 11.sp, color = SakuraPink)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Pilih Materi Kuis:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DeepZenNavy
                )
            }

            items(VocabularyData.categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.startQuiz(category.name, category.items) }
                        .testTag("quiz_category_${category.name.replace(" ", "_")}"),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SakuraLightPink),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = null,
                                tint = HinomaruRed
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Kuis: ${category.name}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = DeepZenNavy
                            )
                            Text(
                                "Berisi 8 pertanyaan pilihan ganda terkait materi.",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Mulai Kuis",
                            tint = DeepZenNavy
                        )
                    }
                }
            }

            if (history.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Riwayat Kuis Sebelumnya:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = DeepZenNavy
                    )
                }

                items(history) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (record.percentage >= 75) CorrectGreen.copy(alpha = 0.2f) else IncorrectOrange.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    if (record.percentage >= 75) "🏅" else "📖",
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    record.categoryName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepZenNavy
                                )
                                Text(
                                    "Benar: ${record.score} dari ${record.totalQuestions} pertanyaan",
                                    fontSize = 11.sp,
                                    color = Color.DarkGray
                                )
                            }
                            Text(
                                "${record.percentage}%",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = if (record.percentage >= 75) CorrectGreen else IncorrectOrange
                            )
                        }
                    }
                }
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Belum ada riwayat kuis. Ambil kuis pertamamu untuk melacak progres! 🌸",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    } else {
        // Quiz Active Mode
        val state = quizState!!
        
        if (state.isQuizFinished) {
            // Show Beautiful Quiz Score Summary
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Kuis Selesai! 🎉",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = HinomaruRed
                        )
                        Text(
                            text = state.categoryName,
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                        )
                        
                        // Large circular score badge
                        val pct = (state.score * 100) / state.questions.size
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(SakuraLightPink)
                                .drawBehind {
                                    drawArc(
                                        brush = Brush.sweepGradient(listOf(SakuraPink, SakuraAccent)),
                                        startAngle = -90f,
                                        sweepAngle = (pct * 360f / 100f),
                                        useCenter = false,
                                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                                            width = 10.dp.toPx()
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${state.score}/${state.questions.size}",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Black,
                                    color = DeepZenNavy
                                )
                                Text(
                                    text = "Skor: $pct%",
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Custom dynamic feedback from Sensei Sakura
                        val motivationalText = when {
                            pct >= 85 -> "Yatta! Sugoi desu ne! 🤩 Kamu menguasai bab ini dengan sempurna sekali. Sensei Sakura bangga padamu! 🌸"
                            pct >= 50 -> "Bagus sekali! Kamu sudah paham banyak materi kosakata Jepang ini. Sedikit latihan lagi kamu pasti bisa 100%, Ganbatte! ✨"
                            else -> "Semangat! Jangan putus asa ya. Bahasa Jepang memang butuh waktu. Teruslah berlatih dengan Sensei Sakura! 🌸"
                        }
                        
                        Card(
                            colors = CardDefaults.cardColors(containerColor = SakuraLightPink),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.padding(12.dp)) {
                                Text("🌸", fontSize = 24.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = motivationalText,
                                    fontSize = 12.sp,
                                    color = DeepZenNavy,
                                    lineHeight = 18.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { showCertificate = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("view_certificate_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = HinomaruRed)
                        ) {
                            Icon(imageVector = Icons.Default.School, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Lihat Sertifikat Kelulusan 📜", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.exitQuiz() },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("quiz_back_to_hub_button"),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepZenNavy)
                    ) {
                        Text("Hub Kuis", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { viewModel.startQuiz(state.categoryName, VocabularyData.categories.find { it.name == state.categoryName }?.items ?: emptyList()) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("quiz_retry_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = DeepZenNavy, contentColor = SakuraPink)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = SakuraPink)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Ulangi Kuis", fontWeight = FontWeight.Bold)
                    }
                }
            }
        } else {
            // Running Quiz Step
            val currentIdx = state.currentQuestionIndex
            val question = state.questions[currentIdx]

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Progress Bar and Info
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pertanyaan ${currentIdx + 1} dari ${state.questions.size}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Bab: ${state.categoryName}",
                            fontSize = 12.sp,
                            color = SakuraAccent,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { (currentIdx.toFloat() / state.questions.size.toFloat()) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = SakuraAccent,
                        trackColor = SakuraPink.copy(alpha = 0.3f)
                    )
                }

                // Question Display Card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = question.questionText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepZenNavy,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp
                            )
                        }
                    }
                }

                // Multiple Choice Answers
                items(question.options.size) { optionIdx ->
                    val optionText = question.options[optionIdx]
                    val isSelected = state.selectedAnswerIndex == optionIdx
                    val isSubmitted = state.isAnswerSubmitted
                    val isCorrectIdx = question.correctAnswerIndex == optionIdx

                    // Define option button colors based on state
                    val cardBg = when {
                        isSubmitted && isCorrectIdx -> CorrectGreen.copy(alpha = 0.15f)
                        isSubmitted && isSelected && !isCorrectIdx -> IncorrectOrange.copy(alpha = 0.15f)
                        isSelected -> SakuraLightPink
                        else -> Color.White
                    }

                    val borderBg = when {
                        isSubmitted && isCorrectIdx -> CorrectGreen
                        isSubmitted && isSelected && !isCorrectIdx -> IncorrectOrange
                        isSelected -> SakuraAccent
                        else -> Color.LightGray.copy(alpha = 0.5f)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(!isSubmitted) { viewModel.selectAnswer(optionIdx) }
                            .testTag("quiz_option_$optionIdx"),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    // Left border accent
                                    drawRect(
                                        color = borderBg,
                                        size = androidx.compose.ui.geometry.Size(
                                            6.dp.toPx(),
                                            size.height
                                        )
                                    )
                                }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) SakuraAccent else Color.LightGray.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A' + optionIdx).toString(),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else Color.DarkGray
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = optionText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = DeepZenNavy
                            )
                        }
                    }
                }

                // Feedback and Submission buttons
                item {
                    val isSubmitted = state.isAnswerSubmitted
                    
                    AnimatedVisibility(
                        visible = isSubmitted,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        val isUserCorrect = state.selectedAnswerIndex == question.correctAnswerIndex
                        
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (isUserCorrect) CorrectGreen.copy(alpha = 0.12f) else IncorrectOrange.copy(alpha = 0.12f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("quiz_feedback_card")
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = if (isUserCorrect) "Benar! Sugoi! 🎉" else "Belum tepat! Ganbatte! 🌸",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (isUserCorrect) CorrectGreen else IncorrectOrange
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = question.explanation,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (!isSubmitted) {
                        Button(
                            onClick = { viewModel.submitAnswer() },
                            enabled = state.selectedAnswerIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("quiz_submit_answer_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = DeepZenNavy, contentColor = SakuraPink)
                        ) {
                            Text("Verifikasi Jawaban", fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Button(
                            onClick = { viewModel.nextQuestion() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("quiz_next_question_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = SakuraAccent)
                        ) {
                            Text(
                                text = if (currentIdx == state.questions.size - 1) "Selesai & Lihat Skor" else "Pertanyaan Berikutnya",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        if (showCertificate && quizState != null && quizState!!.isQuizFinished) {
            QuizCertificateDialog(
                categoryName = quizState!!.categoryName,
                score = quizState!!.score,
                totalQuestions = quizState!!.questions.size,
                onDismiss = { showCertificate = false }
            )
        }
    }
}
}


// ============================================
// SCREEN 4: BOOKMARKS (DICTIONARY PREVIEW)
// ============================================
@Composable
fun BookmarkScreen(viewModel: NihonViewModel) {
    val bookmarks by viewModel.allBookmarks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = SakuraLightPink)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Kamus Kosakata Saya 📝",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepZenNavy
                )
                Text(
                    "Berikut adalah daftar kosakata bahasa Jepang pilihan yang berhasil kamu simpan. Dengarkan pengucapan dan hafal maknanya kapan saja!",
                    fontSize = 11.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp),
                    lineHeight = 16.sp
                )
            }
        }

        if (bookmarks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag("bookmark_empty_state"),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text("📖", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Kamus Khas Kamu Masih Kosong",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = DeepZenNavy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Buka menu 'Belajar', pilih sebuah materi topik, dan ketuk ikon bintang ⭐ di kosakata pilihanmu untuk memasukannya ke kamus personal ini.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(bookmarks) { bookmark ->
                    BookmarkListItem(
                        bookmark = bookmark,
                        onSpeak = { viewModel.speakJapanese(bookmark.japanese) },
                        onRemove = {
                            // Map BookmarkEntity back to helper call
                            viewModel.toggleBookmark(
                                VocabularyItem(bookmark.japanese, bookmark.romaji, bookmark.meaning, ""),
                                bookmark.category,
                                true
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookmarkListItem(
    bookmark: BookmarkEntity,
    onSpeak: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("bookmark_item_${bookmark.japanese}"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSpeak() }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = bookmark.japanese,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = HinomaruRed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "(${bookmark.category})",
                        fontSize = 9.sp,
                        color = SakuraAccent,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(SakuraLightPink, RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = bookmark.romaji,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = bookmark.meaning,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepZenNavy
                )
            }

            IconButton(
                onClick = onSpeak,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SakuraLightPink)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = null,
                    tint = DeepZenNavy,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onRemove,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SakuraLightPink)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Hapus bookmark",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

// --- CERTIFICATE MODULE ---

data class CertificateTheme(
    val categoryName: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val backgroundColor: Color,
    val emoji: String,
    val kanjiTitle: String,
    val certName: String
)

fun getCertificateTheme(category: String): CertificateTheme {
    return when (category) {
        "Salam & Perkenalan" -> CertificateTheme(
            category,
            Color(0xFFE91E63), // Pink
            Color(0xFFFCE4EC), // Light Pink
            Color(0xFFFFF1F3), // Neutral Soft Pink
            "🌸",
            "挨拶と自己紹介", // Aisatsu to jikoshoukai
            "Sertifikat Sapaan & Perkenalan"
        )
        "Angka & Waktu" -> CertificateTheme(
            category,
            Color(0xFF0097A7), // Cyan
            Color(0xFFE0F7FA), // Light Cyan
            Color(0xFFF0FDFF), // Neutral Cyan
            "⏰",
            "数字と時間", // Suuji to jikan
            "Sertifikat Angka & Penunjuk Waktu"
        )
        "Makanan & Restoran" -> CertificateTheme(
            category,
            Color(0xFFD32F2F), // Hinomaru Red
            Color(0xFFFFEBEE), // Light Red
            Color(0xFFFFF5F5), // Neutral Red
            "🍣",
            "飲食とレストラン", // Inshoku to resutoran
            "Sertifikat Kuliner & Restoran Jepang"
        )
        "Percakapan Sehari-hari" -> CertificateTheme(
            category,
            Color(0xFF673AB7), // Purple
            Color(0xFFEDE7F6), // Light Purple
            Color(0xFFF9F6FC), // Neutral Purple
            "💬",
            "日常会話", // Nichijou kaiwa
            "Sertifikat Percakapan Sehari-Hari"
        )
        "Liburan & Perjalanan" -> CertificateTheme(
            category,
            Color(0xFF00796B), // Teal
            Color(0xFFE0F2F1), // Light Teal
            Color(0xFFF4FBFB), // Neutral Teal
            "🗻",
            "旅行と観光", // Ryokou to kankou
            "Sertifikat Perjalanan & Pariwisata"
        )
        "Arah & Navigasi" -> CertificateTheme(
            category,
            Color(0xFFE65100), // Orange
            Color(0xFFFFF3E0), // Light Orange
            Color(0xFFFFFBF7), // Neutral Orange
            "🧭",
            "方向とナビゲーション", // Houkou to nabigeeshon
            "Sertifikat Arah & Navigasi Jepang"
        )
        "Keluarga & Hubungan" -> CertificateTheme(
            category,
            Color(0xFFC2185B), // Deep Rose
            Color(0xFFFCE4EC), // Light Rose
            Color(0xFFFFF2F6), // Neutral Rose
            "💖",
            "家族と人間関係", // Kazoku to ningen kankei
            "Sertifikat Silsilah Keluarga & Sosial"
        )
        "Perasaan & Kesehatan" -> CertificateTheme(
            category,
            Color(0xFF2E7D32), // Green
            Color(0xFFE8F5E9), // Light Green
            Color(0xFFF5FBF5), // Neutral Green
            "🍀",
            "感情と健康", // Kanjou to kenkou
            "Sertifikat Emosi & Kesehatan Jepang"
        )
        "Cuaca & Musim" -> CertificateTheme(
            category,
            Color(0xFFF57F17), // Yellow/Gold
            Color(0xFFFFFDE7), // Light Yellow
            Color(0xFFFFFFF4), // Neutral Yellow
            "☀️",
            "天気と四季", // Tenki to shiki
            "Sertifikat Cuaca & Empat Musim Jepang"
        )
        "Belanja & Pasar" -> CertificateTheme(
            category,
            Color(0xFF7B1FA2), // Violet
            Color(0xFFF3E5F5), // Light Violet
            Color(0xFFFCF7FD), // Neutral Violet
            "🛍️",
            "買い物と市場", // Kaimono to ichiba
            "Sertifikat Transaksi & Belanja"
        )
        "Pendidikan & Sekolah" -> CertificateTheme(
            category,
            Color(0xFF1565C0), // Dark Blue
            Color(0xFFE3F2FD), // Light Blue
            Color(0xFFF0F7FF), // Neutral Blue
            "🎓",
            "教育と学校", // Kyouiku to gakkou
            "Sertifikat Akademik & Istilah Sekolah"
        )
        "Hobi & Rekreasi" -> CertificateTheme(
            category,
            Color(0xFF8E24AA), // Deep Purple
            Color(0xFFF3E5F5), // Light Purple
            Color(0xFFFBF7FC), // Neutral Ash Purple
            "🎮",
            "趣味と娯楽", // Shumi to goraku
            "Sertifikat Kelulusan Hobi & Rekreasi"
        )
        "Hewan & Alam" -> CertificateTheme(
            category,
            Color(0xFF43A047), // Green
            Color(0xFFE8F5E9), // Light Green
            Color(0xFFF6FBF6), // Neutral Sage Green
            "🦁",
            "動物と自然", // Doubutsu to shizen
            "Sertifikat Pengetahuan Fauna & Alam"
        )
        "Pekerjaan & Karir" -> CertificateTheme(
            category,
            Color(0xFF00897B), // Teal
            Color(0xFFE0F2F1), // Light Teal
            Color(0xFFF4FAF9), // Neutral Sky Teal
            "💼",
            "仕事と職業", // Shigoto to shokugyou
            "Sertifikat Penguasaan Karir & Profesi"
        )
        "Warna & Desain" -> CertificateTheme(
            category,
            Color(0xFF3949AB), // Indigo
            Color(0xFFE8EAF6), // Light Indigo
            Color(0xFFF5F6FC), // Neutral Cool White
            "🎨",
            "色彩と形状", // Shikisai to keijou
            "Sertifikat Kosakata Warna & Estetika"
        )
        "Transportasi Umum" -> CertificateTheme(
            category,
            Color(0xFFE53935), // Crimson Red
            Color(0xFFFFEBEE), // Light Red
            Color(0xFFFFF7F7), // Neutral Soft Crimson
            "🚊",
            "交通と車量", // Koutsuu to sharyou
            "Sertifikat Penunjuk Transportasi & Komuter"
        )
        "Rumah & Peralatan" -> CertificateTheme(
            category,
            Color(0xFF6D4C41), // Brown
            Color(0xFFEFEBE9), // Light Brown
            Color(0xFFFBF9F8), // Neutral Warm Gray
            "🏠",
            "家庭と電化製品", // Katei to denkaseihin
            "Sertifikat Perlengkapan Domestik & Rumah"
        )
        else -> CertificateTheme(
            category,
            Color(0xFF3F51B5), // Indigo default
            Color(0xFFE8EAF6),
            Color(0xFFF5F6FC),
            "📜",
            "日本語能力認定", // Nihongo nouryoku nintei
            "Sertifikat Kelulusan Bab Jepang"
        )
    }
}

@Composable
fun QuizCertificateDialog(
    categoryName: String,
    score: Int,
    totalQuestions: Int,
    onDismiss: () -> Unit
) {
    val theme = getCertificateTheme(categoryName)
    val pct = if (totalQuestions > 0) (score * 100) / totalQuestions else 0
    
    val predicate = when {
        pct >= 90 -> Pair("Yû (優 - Sangat Istimewa) 🏆", "Dengan Pujian Tertinggi")
        pct >= 75 -> Pair("Ryô (良 - Sangat Baik) 👑", "Dengan Hasil Sangat Memuaskan")
        pct >= 50 -> Pair("Ka (可 - Cukup/Lulus) 👍", "Lulus Evaluasi")
        else -> Pair("Fuka (不可 - Belum Lulus) 📖", "Partisipasi Evaluasi")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f))
            .clickable(enabled = true, onClick = onDismiss)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .clickable(enabled = false) {}
                .padding(vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = theme.backgroundColor),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawCircle(
                            color = theme.primaryColor.copy(alpha = 0.04f),
                            radius = 180.dp.toPx(),
                            center = Offset(size.width * 0.1f, size.height * 0.2f)
                        )
                        drawCircle(
                            color = theme.primaryColor.copy(alpha = 0.05f),
                            radius = 220.dp.toPx(),
                            center = Offset(size.width * 0.9f, size.height * 0.8f)
                        )
                    }
                    .border(
                        width = 4.dp,
                        brush = Brush.linearGradient(listOf(Color(0xFFD4AF37), Color(0xFF996515))),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(3.dp)
                    .border(
                        width = 1.dp,
                        color = theme.primaryColor.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(17.dp)
                    )
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "修了証書",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = theme.primaryColor,
                    textAlign = TextAlign.Center,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
                )
                
                Text(
                    text = "SERTIFIKAT KELULUSAN",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepZenNavy,
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Box(
                    modifier = Modifier
                        .background(theme.primaryColor.copy(alpha = 0.15f), shape = CircleShape)
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = theme.emoji, fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = categoryName,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = theme.primaryColor
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                Text(
                    text = "Diberikan Kepada Siswa Berprestasi Atas Kelulusannya Pada:",
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))
                
                Text(
                    text = theme.certName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DeepZenNavy,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                
                Text(
                    text = "\"${theme.kanjiTitle}\"",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = theme.primaryColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2.dp)
                )
                
                Spacer(modifier = Modifier.height(14.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "PREDIKAT KELULUSAN",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = predicate.first,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = theme.primaryColor,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                        Text(
                            text = predicate.second,
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("SKOR UJIAN", fontSize = 8.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                                Text("$score dari $totalQuestions", fontSize = 14.sp, color = DeepZenNavy, fontWeight = FontWeight.Black)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("PRESENTASE", fontSize = 8.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                                Text("$pct%", fontSize = 14.sp, color = DeepZenNavy, fontWeight = FontWeight.Black)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("STATUS", fontSize = 8.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                                Text(if (pct >= 50) "LULUS ✓" else "REMEDIAL ⚠", fontSize = 14.sp, color = if (pct >= 50) Color(0xFF2E7D32) else Color(0xFFD32F2F), fontWeight = FontWeight.Black)
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "さくら 先生",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = theme.primaryColor,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(1.dp)
                                .background(Color.Gray)
                        )
                        Text(
                            text = "Sensei Sakura",
                            fontSize = 9.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Text(
                            text = "Guru Utama NihonSensei",
                            fontSize = 8.sp,
                            color = Color.Gray
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .border(width = 2.dp, color = Color(0xFFD32F2F), shape = CircleShape)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .border(width = 1.dp, color = Color(0xFFD32F2F).copy(alpha = 0.5f), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "日本",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFFD32F2F),
                                    lineHeight = 10.sp
                                )
                                Text(
                                    text = "桜印",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFFD32F2F),
                                    lineHeight = 10.sp
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = theme.primaryColor)
                ) {
                    Text("Tutup Sertifikat", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}
