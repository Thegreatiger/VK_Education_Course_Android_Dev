package vk.education.android.dev.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import vk.education.android.dev.domain.model.App

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    appName: String,
    description: String,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (App) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("Карточка приложения") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.primaryContainer).padding(16.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = appName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = "Разработчик", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(48.dp)
            ) {
                Text("Установить")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ВИТРИНА
            ShowcaseSection(onAppClick = onShowcaseAppClick)

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Описание", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}