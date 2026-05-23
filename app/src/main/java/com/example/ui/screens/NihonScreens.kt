package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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

data class AnimeThemeColors(
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val bgLight: Color,
    val teacherEmoji: String,
    val teacherName: String,
    val slogan: String
)

@Composable
fun getAnimeColors(theme: NihonViewModel.AnimeTheme): AnimeThemeColors {
    return when(theme) {
        NihonViewModel.AnimeTheme.SAKURA -> AnimeThemeColors(
            primary = Color(0xFF1E1E2E), // DeepZenNavy
            secondary = Color(0xFFFFB7C5), // SakuraPink
            accent = Color(0xFFFF5277), // SakuraAccent
            bgLight = Color(0xFFFFF0F2), // SakuraLightPink
            teacherEmoji = "🌸",
            teacherName = "Sensei Sakura",
            slogan = "Mari belajar bahasa Jepang bersama Sensei Sakura yang ramah!"
        )
        NihonViewModel.AnimeTheme.NEON -> AnimeThemeColors(
            primary = Color(0xFF0F172A), // Cyber slate navy
            secondary = Color(0xFF8B5CF6), // Neon Purple
            accent = Color(0xFF06B6D4), // Neon Cyan
            bgLight = Color(0xFFF1F5F9), // Slate
            teacherEmoji = "⚡",
            teacherName = "Sensei Kazuto",
            slogan = "Belajar bahasa Jepang seru gaya anime Shounen & game!"
        )
        NihonViewModel.AnimeTheme.MATCHA -> AnimeThemeColors(
            primary = Color(0xFF1C2D1E), // Deep Matcha Green
            secondary = Color(0xFF8FA866), // Matcha Green
            accent = Color(0xFFD4AF37), // Golden Tea Accent
            bgLight = Color(0xFFFAF8F5), // Chibi Cream Warm
            teacherEmoji = "🍵",
            teacherName = "Sensei Midori",
            slogan = "Belajar bahasa Jepang santai & damai di kafe matcha!"
        )
        NihonViewModel.AnimeTheme.FUJI_SENSEI -> AnimeThemeColors(
            primary = Color(0xFF0F2C59), // Deep Fuji Indigo Ocean Navy
            secondary = Color(0xFF80C4E9), // Fuji Soft Sky Blue
            accent = Color(0xFF3887BE), // Majestic Mountain Cobalt Blue
            bgLight = Color(0xFFEFF8FF), // Icy Fresh Soft Blue
            teacherEmoji = "🗻",
            teacherName = "Sensei Shizuka",
            slogan = "Belajar bahasa Jepang seindah panorama puncak Gunung Fuji yang damai!"
        )
        NihonViewModel.AnimeTheme.HINOMARU_SENSEI -> AnimeThemeColors(
            primary = Color(0xFF4A0E17), // Deep Crimson Black Accent
            secondary = Color(0xFFEF4444), // Hibiscus/Cherry Crimson Red
            accent = Color(0xFFBC002D), // Hinomaru Genuine Red
            bgLight = Color(0xFFFFF4F4), // Pure Flag Snowy Red Soft Light
            teacherEmoji = "🎌",
            teacherName = "Sensei Akiko",
            slogan = "Kobarkan semangat belajarmu di bawah kibaran bendera Merah-Putih Jepang!"
        )
    }
}

enum class Screen(val title: String, val icon: ImageVector, val tag: String) {
    BELAJAR("Materi", Icons.Default.MenuBook, "nav_belajar"),
    BUDAYA("Info Budaya", Icons.Default.Translate, "nav_budaya"),
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

    val currentTheme by viewModel.currentTheme.collectAsState()
    val themeColors = getAnimeColors(currentTheme)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(themeColors.accent.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(themeColors.teacherEmoji, fontSize = 24.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(34.dp)
                            ) {
                                // Background Canvas: masterfully drawn Sakura branch borders framing the text WITHOUT touching the actual words.
                                Canvas(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    val w = size.width
                                    val h = size.height

                                    val branchColor = Color(0xFF6D4C41) // Elegant natural wood brown
                                    val pinkPetalColor = Color(0xFFFFC0CB) // Soft pink sakura blossom
                                    val deepSakuraColor = Color(0xFFFF5277) // Rich vibrant sakura pink center

                                    // 1. Right-side framing branch: starts at far right, curves slightly but never crosses past 70% width
                                    val pathRight = Path().apply {
                                        moveTo(w, h * 0.1f)
                                        quadraticBezierTo(w * 0.82f, h * 0.05f, w * 0.78f, h * 0.4f)
                                        moveTo(w * 0.9f, h * 0.15f)
                                        quadraticBezierTo(w * 0.85f, h * -0.05f, w * 0.76f, h * 0.12f)
                                    }
                                    drawPath(
                                        path = pathRight,
                                        color = branchColor,
                                        style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
                                    )

                                    // Right branch cherry blossoms (🌸) placed at branch tips & joints
                                    drawCircle(color = pinkPetalColor, radius = 5.5.dp.toPx(), center = Offset(w * 0.78f, h * 0.4f))
                                    drawCircle(color = deepSakuraColor, radius = 2.2.dp.toPx(), center = Offset(w * 0.78f, h * 0.4f))

                                    drawCircle(color = pinkPetalColor, radius = 4.8.dp.toPx(), center = Offset(w * 0.76f, h * 0.12f))
                                    drawCircle(color = deepSakuraColor, radius = 2.0.dp.toPx(), center = Offset(w * 0.76f, h * 0.12f))

                                    drawCircle(color = pinkPetalColor, radius = 4.0.dp.toPx(), center = Offset(w * 0.85f, h * 0.28f))

                                    // 2. Left-side decorative branch: starts at far left, safely stays under 18% width avoiding the text
                                    val pathLeft = Path().apply {
                                        moveTo(0f, h * 0.2f)
                                        quadraticBezierTo(w * 0.1f, h * 0.15f, w * 0.14f, h * 0.45f)
                                    }
                                    drawPath(
                                        path = pathLeft,
                                        color = branchColor,
                                        style = Stroke(width = 2.0.dp.toPx(), cap = StrokeCap.Round)
                                    )

                                    // Left branch blossoms
                                    drawCircle(color = pinkPetalColor, radius = 5.0.dp.toPx(), center = Offset(w * 0.14f, h * 0.45f))
                                    drawCircle(color = deepSakuraColor, radius = 2.0.dp.toPx(), center = Offset(w * 0.14f, h * 0.45f))

                                    // 3. Falling petals drifting elegantly around the layout, keeping the text area clean
                                    drawOval(
                                        color = pinkPetalColor,
                                        topLeft = Offset(w * 0.05f, h * 0.7f),
                                        size = androidx.compose.ui.geometry.Size(6.dp.toPx(), 4.dp.toPx())
                                    )
                                    drawOval(
                                        color = pinkPetalColor,
                                        topLeft = Offset(w * 0.94f, h * 0.75f),
                                        size = androidx.compose.ui.geometry.Size(5.dp.toPx(), 3.dp.toPx())
                                    )
                                }

                                // Premium front layer text styled beautifully
                                Text(
                                    text = "Belajar Bahasa Jepang",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 17.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .padding(horizontal = 14.dp), // Safe margin ensuring it is never touched/occluded by branches
                                    style = TextStyle(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(Color.White, themeColors.secondary)
                                        ),
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha = 0.45f),
                                            offset = Offset(0f, 4f),
                                            blurRadius = 6f
                                        )
                                    )
                                )
                            }
                            Text(
                                text = "${themeColors.teacherName} 🌸 Ranting Sakura Frame",
                                fontSize = 11.sp,
                                color = themeColors.secondary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 14.dp)
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
                                tint = themeColors.secondary
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
                                tint = themeColors.secondary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColors.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = themeColors.primary,
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
                            selectedIconColor = themeColors.primary,
                            selectedTextColor = Color.White,
                            indicatorColor = themeColors.secondary,
                            unselectedIconColor = themeColors.secondary.copy(alpha = 0.6f),
                            unselectedTextColor = themeColors.secondary.copy(alpha = 0.6f)
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
                Screen.BUDAYA -> BudayaScreen(
                    viewModel = viewModel,
                    onNavigateToSensei = { question ->
                        viewModel.sendMessageToSensei(question)
                        currentScreen = Screen.SENSEI
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
    val currentTheme by viewModel.currentTheme.collectAsState()
    val themeColors = getAnimeColors(currentTheme)
    val bookmarks by viewModel.allBookmarks.collectAsState()

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
                        // A magnificent, crash-proof vector banner dynamically styled based on the selected Sensei character theme
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            themeColors.primary,
                                            themeColors.primary.copy(alpha = 0.85f),
                                            themeColors.accent.copy(alpha = 0.2f)
                                        )
                                    )
                                )
                                .drawBehind {
                                    val w = size.width
                                    val h = size.height
                                    
                                    // 1. Draw Japanese Flag (Hinomaru Red Sun) in the background with gorgeous soft glow
                                    drawCircle(
                                        color = Color(0xFFBC002D).copy(alpha = 0.85f), // Hinomaru Red
                                        radius = h * 0.27f,
                                        center = Offset(w * 0.5f, h * 0.45f)
                                    )
                                    // Soft glowing aura behind the sun
                                    drawCircle(
                                        color = Color(0xFFBC002D).copy(alpha = 0.15f),
                                        radius = h * 0.35f,
                                        center = Offset(w * 0.5f, h * 0.45f)
                                    )

                                    // 2. Draw Mount Fuji Mountain Silhouette overlapping the sun
                                    val fujiPath = Path().apply {
                                        moveTo(w * 0.1f, h)
                                        cubicTo(
                                            x1 = w * 0.32f, y1 = h * 0.85f,
                                            x2 = w * 0.38f, y2 = h * 0.45f,
                                            x3 = w * 0.43f, y3 = h * 0.35f
                                        )
                                        lineTo(w * 0.57f, h * 0.35f)
                                        cubicTo(
                                            x1 = w * 0.62f, y1 = h * 0.45f,
                                            x2 = w * 0.68f, y2 = h * 0.85f,
                                            x3 = w * 0.9f, y3 = h
                                        )
                                        close()
                                    }
                                    
                                    // Draw mountain base with a rich theme gradient
                                    drawPath(
                                        path = fujiPath,
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                themeColors.primary.copy(alpha = 0.95f),
                                                themeColors.accent.copy(alpha = 0.95f)
                                            ),
                                            startY = h * 0.35f,
                                            endY = h
                                        )
                                    )

                                    // 3. Draw snowy white cap for Mount Fuji
                                    val snowCapPath = Path().apply {
                                        moveTo(w * 0.43f, h * 0.35f)
                                        lineTo(w * 0.57f, h * 0.35f)
                                        // right slope
                                        cubicTo(
                                            x1 = w * 0.59f, y1 = h * 0.40f,
                                            x2 = w * 0.61f, y2 = h * 0.46f,
                                            x3 = w * 0.62f, y3 = h * 0.50f
                                        )
                                        // snow patches zig-zag natural line across the mountain
                                        lineTo(w * 0.58f, h * 0.48f)
                                        lineTo(w * 0.55f, h * 0.52f)
                                        lineTo(w * 0.52f, h * 0.47f)
                                        lineTo(w * 0.49f, h * 0.53f)
                                        lineTo(w * 0.46f, h * 0.48f)
                                        lineTo(w * 0.43f, h * 0.51f)
                                        lineTo(w * 0.40f, h * 0.47f)
                                        lineTo(w * 0.38f, h * 0.50f)
                                        // left slope up
                                        cubicTo(
                                            x1 = w * 0.39f, y1 = h * 0.46f,
                                            x2 = w * 0.41f, y2 = h * 0.40f,
                                            x3 = w * 0.43f, y3 = h * 0.35f
                                        )
                                        close()
                                    }

                                    drawPath(
                                        path = snowCapPath,
                                        color = Color.White
                                    )

                                    // 4. Draw elegant decorative clouds in the skies (Japanese art style)
                                    drawRoundRect(
                                        color = Color.White.copy(alpha = 0.12f),
                                        topLeft = Offset(w * 0.15f, h * 0.25f),
                                        size = androidx.compose.ui.geometry.Size(w * 0.18f, h * 0.06f),
                                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(h * 0.03f, h * 0.03f)
                                    )
                                    drawRoundRect(
                                        color = Color.White.copy(alpha = 0.12f),
                                        topLeft = Offset(w * 0.68f, h * 0.28f),
                                        size = androidx.compose.ui.geometry.Size(w * 0.22f, h * 0.06f),
                                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(h * 0.03f, h * 0.03f)
                                    )

                                    // Floating cherry blossoms patterns around the bottom sides
                                    drawCircle(
                                        color = themeColors.accent.copy(alpha = 0.25f),
                                        radius = h * 0.025f,
                                        center = Offset(w * 0.2f, h * 0.72f)
                                    )
                                    drawCircle(
                                        color = themeColors.accent.copy(alpha = 0.2f),
                                        radius = h * 0.015f,
                                        center = Offset(w * 0.23f, h * 0.68f)
                                    )
                                    drawCircle(
                                        color = themeColors.accent.copy(alpha = 0.25f),
                                        radius = h * 0.020f,
                                        center = Offset(w * 0.8f, h * 0.75f)
                                    )
                                    drawCircle(
                                        color = themeColors.accent.copy(alpha = 0.2f),
                                        radius = h * 0.015f,
                                        center = Offset(w * 0.77f, h * 0.81f)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "日本語",
                                    fontSize = 42.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White.copy(alpha = 0.18f),
                                    letterSpacing = 10.sp
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "N I H O N G O",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = themeColors.secondary.copy(alpha = 0.9f),
                                    letterSpacing = 5.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha = 0.3f),
                                            offset = Offset(0f, 2f),
                                            blurRadius = 4f
                                        )
                                    )
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "日本へようこそ！ ${themeColors.teacherEmoji}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = themeColors.primary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                "Selamat datang di Belajar Bahasa jepang! Pilih salah satu topik pelajaran di bawah untuk mempelajari kosakata dasar sehari-hari bersama ${themeColors.teacherName} lengkap dengan Romaji dan arti bahasa Indonesianya.",
                                fontSize = 13.sp,
                                color = Color.DarkGray,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                // --- TAB ANIMATE CHARACTER SELECTOR ---
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = themeColors.bgLight),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, themeColors.accent.copy(alpha = 0.4f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🎭", fontSize = 22.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pilih Karakter Tema Anime:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = themeColors.primary
                            )
                        }
                        Text(
                            text = "Ganti guru virtual dan seluruh harmoni visual aplikasi!",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            NihonViewModel.AnimeTheme.values().forEach { theme ->
                                val isSelected = currentTheme == theme
                                val itemColors = getAnimeColors(theme)
                                Button(
                                    onClick = { viewModel.selectTheme(theme) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSelected) itemColors.primary else Color.White,
                                        contentColor = if (isSelected) Color.White else itemColors.primary
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = if (isSelected) itemColors.primary else itemColors.secondary
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(40.dp)
                                        .testTag("theme_btn_${theme.name}"),
                                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${theme.emoji} ${theme.displayName.split(" ")[0]}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Pilihan Kategori Belajar:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = themeColors.primary
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
    val currentTheme by viewModel.currentTheme.collectAsState()
    val themeColors = getAnimeColors(currentTheme)

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
        "Koreksi kalimat saya: 'Watashi no namae desu Kenji' 🧠"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(themeColors.bgLight)
    ) {
        // Sensei Avatar Info Panel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(themeColors.primary)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(themeColors.secondary.copy(alpha = 0.35f)),
                contentAlignment = Alignment.Center
            ) {
                Text(themeColors.teacherEmoji, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    themeColors.teacherName,
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
                        color = themeColors.secondary
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
                        "Memuat obrolan dengan ${themeColors.teacherName}...",
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
                            "Tanya Grammar Partikel 🌸" -> "Tolong ajarkan dan beri contoh simpel tentang perbedaan partikel wa (は) dan ga (gā)?"
                            "Aturan Penulisan Jepang 🎌" -> "Jelaskan apa perbedaan kegunaan huruf Hiragana, Katakana, dan Kanji dan kapan kita menggunakannya?"
                            "Percakapan di Restoran 🍣" -> "Ayo kita simulasi latihan percakapan memesan sushi di restoran Jepang. Mulai obrolan menggunakannya ya!"
                            else -> "Tolong koreksi tata bahasa kalimat Jepang saya yang satu ini dan jelaskan salahnya di mana: 'Watashi no namae desu Kenji.'"
                        }
                        viewModel.sendMessageToSensei(finalPrompt)
                    },
                    label = { Text(question, fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = themeColors.secondary.copy(alpha = 0.25f),
                        labelColor = themeColors.primary
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
                placeholder = { Text("Tanya ${themeColors.teacherName} tentang apa saja...", fontSize = 13.sp) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("chat_input_field"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = themeColors.accent,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = themeColors.bgLight.copy(alpha = 0.5f),
                    unfocusedContainerColor = themeColors.bgLight.copy(alpha = 0.5f)
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
                    .background(themeColors.primary)
                    .testTag("send_message_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Kirim pesan",
                    tint = themeColors.secondary,
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
    val currentTheme by viewModel.currentTheme.collectAsState()
    val themeColors = getAnimeColors(currentTheme)
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
                    colors = CardDefaults.cardColors(containerColor = themeColors.primary),
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
                            color = themeColors.secondary
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
                                Text("Selesai", fontSize = 11.sp, color = themeColors.secondary)
                            }
                            
                            val bestScore = history.maxOfOrNull { it.percentage } ?: 0
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "$bestScore%",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text("Skor Terbaik", fontSize = 11.sp, color = themeColors.secondary)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                // --- INTEGRATED TRASH CAN / RESET EXAM FUNCTIONALITY ---
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (history.isNotEmpty()) Color.Red.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable(enabled = history.isNotEmpty()) {
                            viewModel.clearQuizHistory()
                        }
                        .testTag("reset_quizzes_trash_button"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (history.isNotEmpty()) Color(0xFFFFF5F5) else Color(0xFFF9FAFB)
                    ),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (history.isNotEmpty()) Color(0xFFFFE3E3) else Color(0xFFE5E7EB)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Tong Sampah Reset Kuis",
                                tint = if (history.isNotEmpty()) Color.Red else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Tong Sampah: Reset Semua Ujian 🗑️",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (history.isNotEmpty()) Color(0xFFC53030) else Color.Gray
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (history.isNotEmpty()) 
                                    "Klik untuk membersihkan nilai riwayat dan meriset ujian ke awal semula sebelum dikerjakan!" 
                                    else "Belum ada ujian/kuis dikerjakan. Riwayat masih bersih!",
                                fontSize = 11.sp,
                                color = if (history.isNotEmpty()) Color.DarkGray else Color.Gray,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Pilih Materi Kuis:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = themeColors.primary
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

// ============================================
// SCREEN 5: BUDAYA & INFO JEPANG (SEJARAH, BAHASA, KEHIDUPAN)
// ============================================

data class CultureArticle(
    val id: String,
    val title: String,
    val rtitle: String,
    val category: String, // "Sejarah", "Bahasa", "Kehidupan"
    val iconEmoji: String,
    val summary: String,
    val details: String,
    val triviaQuestion: String,
    val triviaOptions: List<String>,
    val triviaCorrectIndex: Int,
    val vocabItems: List<com.example.data.model.VocabularyItem>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudayaScreen(
    viewModel: NihonViewModel,
    onNavigateToSensei: (String) -> Unit
) {
    val currentTheme by viewModel.currentTheme.collectAsState()
    val themeColors = getAnimeColors(currentTheme)
    val bookmarks by viewModel.allBookmarks.collectAsState()

    val articles = remember {
        listOf(
            CultureArticle(
                id = "samurai_edo",
                title = "Zaman Shogun & Kasta Samurai (Era Edo)",
                rtitle = "江戸時代 (Edo Jidai)",
                category = "Sejarah",
                iconEmoji = "🏯",
                summary = "Era kejayaan klan samurai di bawah kekuasaan militer Tokugawa Shogunate.",
                details = "Zaman Sengoku merupakan periode perang saudara yang berkepanjangan di Jepang sebelum akhirnya dipersatukan di era Edo di bawah pimpinan Shogun Tokugawa Ieyasu. Pada zaman Edo (1603-1867), Jepang menjalani era perdamaian selama lebih dari 250 tahun. Selama periode ini Jepang menerapkan kebijakan isolasi total (Sakoku) dari dunia luar. Kasta Samurai menempati urutan tertinggi dalam struktur sosial, memegang teguh filosofi kehormatan 'Bushido' (jalan ksatria) yang mengajarkan kesetiaan mutlak, ketenangan diri, dan disiplin tinggi.",
                triviaQuestion = "Pemimpin militer tertinggi yang menguasai Jepang di era feudal disebut...",
                triviaOptions = listOf("Kaisar (Tennou)", "Shogun", "Daimyo"),
                triviaCorrectIndex = 1,
                vocabItems = listOf(
                    VocabularyItem("侍 (さむらい)", "Samurai", "Prajurit Pedang Jepang", "Ksatria elit Jepang yang sangat memegang teguh kode etik Bushido."),
                    VocabularyItem("将軍 (しょうぐん)", "Shogun", "Jenderal Militer tertinggi", "Gelar untuk penguasa militer tunggal Jepang era Edo."),
                    VocabularyItem("城 (しろ)", "Shiro", "Kastil / Istana", "Benteng pertahanan megah para tuan tanah feudal di Jepang.")
                )
            ),
            CultureArticle(
                id = "meiji_restorasi",
                title = "Modernisasi Kilat & Restorasi Meiji",
                rtitle = "明治維新 (Meiji Ishin)",
                category = "Sejarah",
                iconEmoji = "🚂",
                summary = "Kelahiran Jepang modern pasca runtuhnya kekuasaan feudal Samurai.",
                details = "Restorasi Meiji terjadi pada tahun 1868, menandai runtuhnya Shogunate Tokugawa dan mengembalikan kekuasaan pemerintahan langsung kepada Kaisar Meiji. Langkah ini mengawali modernisasi kilat Jepang dari negara feodal yang tertutup menjadi raksasa industri modern. Jepang mengirim utusan ke seluruh dunia untuk menyerap sistem hukum, taktik militer Barat, sains, dan teknologi kereta api. Akibat modernisasi ini, kasta Samurai secara resmi dibubarkan dan digantikan oleh militer berbasis wajib militer modern.",
                triviaQuestion = "Kapan Restorasi Meiji yang mengakhiri zaman Shogun terjadi?",
                triviaOptions = listOf("Tahun 1603", "Tahun 1868", "Tahun 1945"),
                triviaCorrectIndex = 1,
                vocabItems = listOf(
                    VocabularyItem("天皇 (てんのう)", "Tennou", "Kaisar Jepang", "Simbol kesatuan dan kepala negara seremonial tertinggi Kekaisaran Jepang."),
                    VocabularyItem("変革 (へんかく)", "Henkaku", "Reformasi / Perubahan besar", "Transformasi sosial-budaya pasca berakhirnya shogun."),
                    VocabularyItem("明治 (めいじ)", "Meiji", "Zaman Meiji", "Periode restorasi yang bermakna 'Pemerintahan Terang'.")
                )
            ),
            CultureArticle(
                id = "tiga_aksara",
                title = "Keajaiban Tiga Aksara Bahasa Jepang",
                rtitle = "文字 (Moji)",
                category = "Bahasa",
                iconEmoji = "✍️",
                summary = "Harmoni penulisan unik gabungan Hiragana, Katakana, dan Kanji.",
                details = "Bahasa Jepang adalah satu-satunya bahasa di dunia yang secara aktif menggabungkan tiga aksara dalam satu kalimat: Hiragana, Katakana, dan Kanji. \nHiragana (ひらがな) digunakan untuk menulis tata bahasa asli Jepang dan partikel.\nKatakana (カタカナ) memiliki coretan kaku untuk menulis nama asing, negara, atau kata serapan.\nKanji (漢字) adalah karakter ideogram Tiongkok yang masing-masing melambangkan objek atau simbol makna yang luas. Gabungan ketiganya membuat penulisan menjadi ringkas dan artistik.",
                triviaQuestion = "Aksara manakah yang digunakan untuk menulis kata asli Jepang atau partikel tata bahasa?",
                triviaOptions = listOf("Hiragana", "Katakana", "Kanji"),
                triviaCorrectIndex = 0,
                vocabItems = listOf(
                    VocabularyItem("仮名 (かな)", "Kana", "Kategori Aksara Kata", "Sebutan umum untuk huruf suku kata Hiragana dan Katakana."),
                    VocabularyItem("日本語 (にほんご)", "Nihongo", "Bahasa Jepang", "Bahasa resmi dan lambang keramahtamahan warga Jepang."),
                    VocabularyItem("漢字 (かんじ)", "Kanji", "Karakter Kanji", "Aksara berbasis gambar ideogram yang kaya konsep filosofis.")
                )
            ),
            CultureArticle(
                id = "logika_keigo",
                title = "Kecerdasan Tata Bahasa & Logika Keigo",
                rtitle = "敬語 (Keigo)",
                category = "Bahasa",
                iconEmoji = "🤝",
                summary = "Etika tingkat kesopanan berbicara disesuaikan strata sosial mitra bicara.",
                details = "Jika bahasa Indonesia umumnya menggunakan kesopanan intonasi dan pilihan kata santun, bahasa Jepang memiliki sistem tata bahasa khusus bernama Keigo (敬語). Keigo dibagi menjadi tiga kategori utama:\n1. Teineigo (丁寧語): Bentuk sopan standar berakhiran 'Desu/Masu' untuk khalayak umum.\n2. Sonkeigo (尊敬語): Bentuk meninggikan/menghormati tindakan orang lain.\n3. Kenjougo (謙譲語): Bentuk merendahkan tindakan diri sendiri demi menaikkan derajat orang lain secara hormat. Keigo melambangkan filosofi peduli sesama dan keramahtamahan tertinggi (Omotenashi).",
                triviaQuestion = "Gaya bicara Keigo yang merendahkan peran diri sendiri disebut...",
                triviaOptions = listOf("Teineigo", "Sonkeigo", "Kenjougo"),
                triviaCorrectIndex = 2,
                vocabItems = listOf(
                    VocabularyItem("丁寧 (ていねい)", "Teinei", "Sopan Standard / Ramah", "Gaya bahasa netral sehari-hari yang paling aman dan praktis bagi turis."),
                    VocabularyItem("謙譲 (けんじょう)", "Kenjou", "Merendahkan Diri", "Bentuk kata mengecilkan tindakan diri pribadi demi menghormat lawan."),
                    VocabularyItem("尊敬 (そんけい)", "Sonkei", "Rasa Hormat", "Gaya bahasa memuliakan posisi dan peran mitra bicara.")
                )
            ),
            CultureArticle(
                id = "etika_sopan",
                title = "Etika Menakjubkan & Sopan Santun Jepang",
                rtitle = "マナー (Manaa)",
                category = "Kehidupan",
                iconEmoji = "🥢",
                summary = "Tata krama restoran, aturan makan, dan budaya ramah tanpa uang tip.",
                details = "Kehidupan sosial di Jepang dibentuk oleh kesadaran menjaga kedamaian umum (Meiwaku - menghindari perbuatan yang merugikan publik). Saat makan di Jepang, ucapkan 'Itadakimasu' sebagai tanda syukur, dan hindari menancapkan sumpit tegak lurus di mangkok nasi karena menyerupai sesaji pemakaman. Satu hal fenomenal: Jangan pernah meninggalkan uang tip di restoran! Pekerja Jepang meyakini bahwa pelayanan prima adalah harga diri profesional mereka. Memberi tip dapat dipandang kurang sopan karena dianggap meremehkan dedikasi tulus mereka.",
                triviaQuestion = "Mengapa meletakkan sumpit berdiri vertikal di atas miring mangkok nasi dilarang keras?",
                triviaOptions = listOf("Sumpit kotor", "Mirip sesajen ritus pemakaman", "Sumpit pecah"),
                triviaCorrectIndex = 1,
                vocabItems = listOf(
                    VocabularyItem("箸 (はし)", "Hashi", "Sumpit makan", "Alat silindris sepasang untuk bersantap ramah lingkungan."),
                    VocabularyItem("無料 (むりょう)", "Muryou", "Gratis / Bebas Biaya", "Penyajian air putih atau teh hijau (ocha) gratis di kedai makan."),
                    VocabularyItem("お辞儀 (おじぎ)", "Ojigi", "Membungkukkan Badan", "Gestur membungkuk khas tanda salam hangat, bersyukur, atau maaf.")
                )
            ),
            CultureArticle(
                id = "presisi_komuter",
                title = "Kedisiplinan Waktu Komuter & Shinkansen",
                rtitle = "時間 (Jikan)",
                category = "Kehidupan",
                iconEmoji = "🚄",
                summary = "Presisi mili-detik transportasi kereta peluru yang mendidik bangsa disiplin.",
                details = "Sistem transportasi umum Jepang, khususnya Shinkansen (kereta peluru), adalah salah satu mahakarya rekayasa terbaik dunia. Rata-rata keterlambatan tahunan kereta peluru berada di kisaran di bawah 30 detik! Kedisplinan waktu dihormati secara mutlak. Bila kereta terlambat di atas satu menit saja, pihak stasiun akan menyiarkan permohonan maaf dan membagikan kartu fisik 'Sertifikat Terlambat' (Chien-shou) agar para komuter punya bukti resmi sehingga tidak disanksi guru atau atasan kantor.",
                triviaQuestion = "Surat apa yang dibagikan stasiun jika kereta terlambat agar komuter aman dari hukuman?",
                triviaOptions = listOf("Kupon diskon makanan", "Sertifikat bukti terlambat", "Karcis kereta gratis"),
                triviaCorrectIndex = 1,
                vocabItems = listOf(
                    VocabularyItem("切符 (きっぷ)", "Kippu", "Tiket gerbang", "Kupon penampang resmi sebelum melintasi gerbang tiket stasiun."),
                    VocabularyItem("新幹線 (しんかんせん)", "Shinkansen", "Kereta Peluru Shinkansen", "Kereta berkecepatan tinggi ikon kebanggaan industri perkeretaapian."),
                    VocabularyItem("時間通り (じかんどおり)", "Jikan doori", "Tepat Waktu", "Prinsip utama kesetiaan serta penghargaan atas waktu orang lain.")
                )
            )
        )
    }

    var selectedCategoryFilter by remember { mutableStateOf("Semua") }
    var searchQuery by remember { mutableStateOf("") }
    var expandedArticleId by remember { mutableStateOf<String?>(null) }

    // Map to keep track of answered trivia selections for each article
    var selectedTriviaAnswers by remember { mutableStateOf(mapOf<String, Int>()) }

    val filteredArticles = remember(selectedCategoryFilter, searchQuery) {
        articles.filter { article ->
            val matchesCategory = selectedCategoryFilter == "Semua" || article.category.equals(selectedCategoryFilter, ignoreCase = true)
            val matchesSearch = article.title.contains(searchQuery, ignoreCase = true) ||
                    article.summary.contains(searchQuery, ignoreCase = true) ||
                    article.rtitle.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Portal Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = themeColors.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Budaya & Cerita Jepang 🎌",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = themeColors.secondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Jelajahi Sejarah, karakteristik Bahasa, dan keunikan Kehidupan sehari-hari di negeri matahari terbit.",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Control and Filter Chips Row
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Cari topik sejarah, bahasa, kehidupan...", fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("culture_search_bar"),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = themeColors.accent,
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color(0xFF1E1E2E),
                unfocusedTextColor = Color(0xFF1E1E2E)
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category Selection Row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val categories = listOf("Semua", "Sejarah", "Bahasa", "Kehidupan")
            items(categories) { cat ->
                val isSelected = selectedCategoryFilter == cat
                val bgColor = if (isSelected) themeColors.accent else Color.White
                val textColor = if (isSelected) Color.White else DeepZenNavy
                val borderStroke = if (isSelected) null else BorderStroke(1.dp, Color.LightGray)

                Card(
                    modifier = Modifier
                        .clickable { selectedCategoryFilter = cat }
                        .testTag("culture_tab_${cat.lowercase()}"),
                    colors = CardDefaults.cardColors(containerColor = bgColor),
                    border = borderStroke,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = when(cat) {
                            "Sejarah" -> "🏯 Sejarah"
                            "Bahasa" -> "🗣️ Bahasa"
                            "Kehidupan" -> "🍣 Kehidupan"
                            else -> "🌎 Semua"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Empty Search State
        if (filteredArticles.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🔍", fontSize = 44.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Topik Tidak Ditemukan",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Coba gunakan kata kunci sejarah/manner lain.",
                        color = Color.LightGray,
                        fontSize = 11.sp
                    )
                }
            }
        } else {
            // Recycler List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredArticles) { article ->
                    val isExpanded = expandedArticleId == article.id
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = if (isExpanded) 1.5.dp else 1.dp,
                                color = if (isExpanded) themeColors.accent else Color.Gray.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .testTag("culture_article_card_${article.id}"),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = if (isExpanded) 4.dp else 1.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Article Card Header Row (Tappable always to toggle expand)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        expandedArticleId = if (isExpanded) null else article.id
                                    }
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(themeColors.bgLight),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(article.iconEmoji, fontSize = 24.sp)
                                }
                                
                                Spacer(modifier = Modifier.width(12.dp))
                                
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = article.category.uppercase(),
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Black,
                                            color = themeColors.accent,
                                            letterSpacing = 0.5.sp
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = article.rtitle,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Gray
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = article.title,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = DeepZenNavy,
                                        maxLines = if (isExpanded) 3 else 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = article.summary,
                                        fontSize = 11.sp,
                                        color = Color.DarkGray,
                                        maxLines = if (isExpanded) 5 else 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }

                            // Expanded Content Details Block
                            AnimatedVisibility(
                                visible = isExpanded,
                                enter = expandVertically() + fadeIn(),
                                exit = shrinkVertically() + fadeOut()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(themeColors.bgLight.copy(alpha = 0.3f))
                                        .padding(horizontal = 14.dp, vertical = 4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(Color.Gray.copy(alpha = 0.15f))
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Main Story paragraph
                                    Text(
                                        text = "📖 Cerita & Wawasan:",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black,
                                        color = themeColors.primary
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = article.details,
                                        fontSize = 12.sp,
                                        color = Color(0xFF2E2E2E),
                                        lineHeight = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Vocab List Section
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "📑 Kosakata Menarik:",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Black,
                                            color = themeColors.primary
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "(Bisa didengar & dibookmark)",
                                            fontSize = 9.sp,
                                            color = Color.Gray
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Vocabs Render Table
                                    article.vocabItems.forEach { vocab ->
                                        val isBookmarked = bookmarks.any { it.japanese == vocab.japanese }
                                        
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp),
                                            colors = CardDefaults.cardColors(containerColor = Color.White),
                                            shape = RoundedCornerShape(10.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Text(
                                                            text = vocab.japanese,
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = HinomaruRed
                                                        )
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text(
                                                            text = vocab.romaji,
                                                            fontSize = 11.sp,
                                                            color = Color.Gray,
                                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.height(2.dp))
                                                    Text(
                                                        text = vocab.meaning,
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = DeepZenNavy
                                                    )
                                                    Text(
                                                        text = vocab.notes,
                                                        fontSize = 10.sp,
                                                        color = Color.Gray,
                                                        lineHeight = 13.sp
                                                    )
                                                }

                                                // Volume Audio speak button
                                                IconButton(
                                                    onClick = { viewModel.speakJapanese(vocab.japanese) },
                                                    modifier = Modifier.size(32.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.VolumeUp,
                                                        contentDescription = "Suara",
                                                        tint = themeColors.accent,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }

                                                // Bookmark Star Toggle
                                                IconButton(
                                                    onClick = {
                                                        viewModel.toggleBookmark(
                                                            item = vocab,
                                                            category = "Budaya - ${article.category}",
                                                            isAlreadyBookmarked = isBookmarked
                                                        )
                                                    },
                                                    modifier = Modifier.size(32.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = if (isBookmarked) Icons.Default.Star else Icons.Default.BookmarkBorder,
                                                        contentDescription = "Bookmark",
                                                        tint = if (isBookmarked) Color(0xFFFFD700) else Color.Gray,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Mini Interactive Trivia Quiz Panel
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(1.dp, themeColors.accent.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(
                                                text = "⚡ Kuis Trivia Kilat:",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Black,
                                                color = themeColors.accent
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = article.triviaQuestion,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = DeepZenNavy
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))

                                            val answeredIndex = selectedTriviaAnswers[article.id]
                                            article.triviaOptions.forEachIndexed { optIdx, option ->
                                                val isCorrectAnswer = optIdx == article.triviaCorrectIndex
                                                val isSelectedByMe = answeredIndex == optIdx

                                                val buttonBgColor = when {
                                                    answeredIndex == null -> Color.White
                                                    isSelectedByMe && isCorrectAnswer -> Color(0xFFE8F5E9)
                                                    isSelectedByMe && !isCorrectAnswer -> Color(0xFFFFEBEE)
                                                    isCorrectAnswer -> Color(0xFFE8F5E9)
                                                    else -> Color.White
                                                }

                                                val buttonBorderColor = when {
                                                    answeredIndex == null -> Color.LightGray.copy(alpha = 0.5f)
                                                    isSelectedByMe && isCorrectAnswer -> CorrectGreen
                                                    isSelectedByMe && !isCorrectAnswer -> Color.Red
                                                    isCorrectAnswer -> CorrectGreen
                                                    else -> Color.LightGray.copy(alpha = 0.3f)
                                                }

                                                Card(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 3.dp)
                                                        .clickable(enabled = answeredIndex == null) {
                                                            selectedTriviaAnswers = selectedTriviaAnswers.toMutableMap().apply {
                                                                put(article.id, optIdx)
                                                            }
                                                        },
                                                    colors = CardDefaults.cardColors(containerColor = buttonBgColor),
                                                    border = BorderStroke(1.dp, buttonBorderColor),
                                                    shape = RoundedCornerShape(8.dp)
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(10.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = when(optIdx) {
                                                                0 -> "A. $option"
                                                                1 -> "B. $option"
                                                                else -> "C. $option"
                                                            },
                                                            fontSize = 11.sp,
                                                            color = DeepZenNavy,
                                                            fontWeight = if (isSelectedByMe) FontWeight.Bold else FontWeight.Normal,
                                                            modifier = Modifier.weight(1f)
                                                        )
                                                        
                                                        if (answeredIndex != null) {
                                                            if (isCorrectAnswer) {
                                                                Text("✓", color = CorrectGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                                            } else if (isSelectedByMe) {
                                                                Text("✗", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            // Feedback msg
                                            if (answeredIndex != null) {
                                                val answeredCorrectly = answeredIndex == article.triviaCorrectIndex
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = if (answeredCorrectly) "Hebat! Jawaban Anda Benar 🎉" else "Jawaban kurang tepat. Coba ulangi kuis! 💡",
                                                        fontSize = 11.sp,
                                                        color = if (answeredCorrectly) CorrectGreen else IncorrectOrange,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    
                                                    Text(
                                                        text = "RESET KUIS",
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Black,
                                                        color = themeColors.accent,
                                                        modifier = Modifier
                                                            .clickable {
                                                                selectedTriviaAnswers = selectedTriviaAnswers.toMutableMap().apply {
                                                                    remove(article.id)
                                                                }
                                                            }
                                                            .background(themeColors.accent.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // Ask AI Connection Button (Sends contextual question to Sensei AI screen)
                                    Button(
                                        onClick = {
                                            val prompt = "Sensei, saya baru membaca artikel tentang '${article.title}' (${article.rtitle}) di modul Budaya. Tolong berikan saya penjelasan atau fakta unik menarik lainnya tentang ini, dan ajarkan saya 1 kosakata baru terkait tema ini!"
                                            onNavigateToSensei(prompt)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 8.dp)
                                            .testTag("ask_sensei_btn_${article.id}"),
                                        colors = ButtonDefaults.buttonColors(containerColor = themeColors.primary)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Chat,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Tanya Sensei AI tentang Topik ini 💬",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

