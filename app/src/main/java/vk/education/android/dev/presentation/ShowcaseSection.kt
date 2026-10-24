package vk.education.android.dev.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import vk.education.android.dev.domain.model.App

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowcaseSection(onAppClick: (App) -> Unit) {
    // Хардкодим 9 приложений для витрины (используем нашу Domain-модель)
    val showcaseApps = listOf(
        App("5", "QuickNote", "Заметки", "Продуктивность"),
        App("6", "Calmalist", "Списки", "Продуктивность"),
        App("7", "FitRiver", "Фитнес", "Здоровье"),
        App("8", "TaskLoop", "Задачи", "Продуктивность"),
        App("9", "Pixtrix", "Фото", "Фотография"),
        App("10", "TinyChef", "Рецепты", "Еда"),
        App("11", "GymMate", "Спорт", "Спорт"),
        App("12", "FlowCard", "Финансы", "Финансы"),
        App("13", "DreamSafe", "Безопасность", "Утилиты")
    )

    val pages = showcaseApps.chunked(3)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        val pagerState = rememberPagerState(pageCount = { pages.size })

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                pages[page].forEach { app ->
                    Column(
                        modifier = Modifier.weight(1f).clickable { onAppClick(app) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = app.name,
                            modifier = Modifier.size(72.dp).background(MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium).padding(12.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = app.name, style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center, maxLines = 1)
                    }
                }
            }
        }
    }
}