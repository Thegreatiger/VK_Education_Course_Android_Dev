package vk.education.android.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Модель одного приложения в списке.
 */
data class AppInfo(
    val icon: ImageVector,
    val name: String,
    val description: String,
    val category: String,
)

/**
 * Захардкоженные данные.
 * В следующих практиках заменим на реальные данные из ViewModel.
 */
val sampleApps = listOf(
    AppInfo(Icons.Default.Android, "VK", "Социальная сеть", "Соцсети"),
    AppInfo(Icons.Default.Android, "RuStore", "Магазин приложений", "Инструменты"),
    AppInfo(Icons.Default.Android, "VK Видео", "Смотрите видео и стримы", "Медиа"),
    AppInfo(Icons.Default.Android, "VK Музыка", "Слушайте любимые треки", "Медиа"),
    AppInfo(Icons.Default.Android, "VK Мессенджер", "Общайтесь с друзьями", "Соцсети"),
    AppInfo(Icons.Default.Android, "Почта Mail.ru", "Электронная почта", "Инструменты"),
    AppInfo(Icons.Default.Android, "Дзен", "Новости и статьи", "Новости"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppListScreen()
            }
        }
    }
}

@Composable
fun AppListScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolbar()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sampleApps) { app ->
                AppListItem(app)
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}

/**
 * Тулбар: иконка приложения + название. Фон — акцентный (primary).
 */
@Composable
fun AppToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = "Иконка приложения",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "RuStore",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Элемент списка: иконка + название + описание + чип категории.
 */
@Composable
fun AppListItem(app: AppInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Иконка приложения
        Icon(
            imageVector = app.icon,
            contentDescription = app.name,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))
        CategoryChip(app.category)
    }
}

@Composable
fun CategoryChip(category: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}