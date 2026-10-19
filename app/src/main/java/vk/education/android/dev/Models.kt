package vk.education.android.dev

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class AppInfo(
    val id: String,
    val icon: ImageVector,
    val name: String,
    val description: String,
    val category: String
)

val sampleApps = listOf(
    AppInfo("1", Icons.Default.Home, "VK", "Социальная сеть", "Соцсети"),
    AppInfo("2", Icons.Default.Home, "RuStore", "Магазин приложений", "Инструменты"),
    AppInfo("3", Icons.Default.Home, "VK Видео", "Смотрите видео", "Медиа"),
    AppInfo("4", Icons.Default.Home, "VK Музыка", "Слушайте треки", "Медиа")
)

val showcaseApps = listOf(
    AppInfo("5", Icons.Default.Home, "QuickNote", "Заметки", "Продуктивность"),
    AppInfo("6", Icons.Default.Home, "Calmalist", "Списки", "Продуктивность"),
    AppInfo("7", Icons.Default.Home, "FitRiver", "Фитнес", "Здоровье"),
    AppInfo("8", Icons.Default.Home, "TaskLoop", "Задачи", "Продуктивность"),
    AppInfo("9", Icons.Default.Home, "Pixtrix", "Фото", "Фотография"),
    AppInfo("10", Icons.Default.Home, "TinyChef", "Рецепты", "Еда"),
    AppInfo("11", Icons.Default.Home, "GymMate", "Спорт", "Спорт"),
    AppInfo("12", Icons.Default.Home, "FlowCard", "Финансы", "Финансы"),
    AppInfo("13", Icons.Default.Home, "DreamSafe", "Безопасность", "Утилиты")
)