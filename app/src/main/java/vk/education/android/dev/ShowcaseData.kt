package vk.education.android.dev

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class ShowcaseApp(
    val icon: ImageVector,
    val name: String,
    val description: String,
    val category: String
)

val showcasePages: List<List<ShowcaseApp>> = listOf(
    listOf(
        ShowcaseApp(Icons.Default.Home, "VK", "Соцсеть", "Соцсети"),
        ShowcaseApp(Icons.Default.Home, "RuStore", "Магазин", "Инструменты"),
        ShowcaseApp(Icons.Default.Home, "VK Видео", "Видео", "Медиа"),
    ),
    listOf(
        ShowcaseApp(Icons.Default.Home, "VK Музыка", "Музыка", "Медиа"),
        ShowcaseApp(Icons.Default.Home, "VK Мессенджер", "Чат", "Соцсети"),
        ShowcaseApp(Icons.Default.Home, "Почта", "Почта", "Инструменты"),
    ),
    listOf(
        ShowcaseApp(Icons.Default.Home, "Дзен", "Новости", "Новости"),
        ShowcaseApp(Icons.Default.Home, "VK Клипы", "Клипы", "Медиа"),
        ShowcaseApp(Icons.Default.Home, "VK Знакомства", "Знакомства", "Соцсети"),
    )
)