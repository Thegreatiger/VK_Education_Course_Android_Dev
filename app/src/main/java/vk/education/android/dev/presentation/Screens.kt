package vk.education.android.dev.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vk.education.android.dev.domain.InstallState

@Composable
fun AppListScreen(
    onAppClick: (String) -> Unit,
    viewModel: AppListViewModel = hiltViewModel()
) {
    val apps by viewModel.apps.collectAsState()
    val timeLog by viewModel.timeLog.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Замеры времени:", style = MaterialTheme.typography.titleMedium)
        Text(timeLog, color = MaterialTheme.colorScheme.primary)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.loadRatingsSequentially() }) {
                Text("Последовательно")
            }
            Button(onClick = { viewModel.loadRatingsParallel() }) {
                Text("Параллельно")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(apps) { app ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    onClick = { onAppClick(app.id) }
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(app.name, modifier = Modifier.weight(1f))
                        Text(text = "⭐ ${app.rating ?: "..."}")
                    }
                }
            }
        }
    }
}

@Composable
fun AppDetailsScreen(
    onBackClick: () -> Unit,
    viewModel: AppDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = onBackClick) { Text("Назад") }

        Spacer(modifier = Modifier.height(16.dp))

        Text(state.details?.name ?: "Загрузка...", style = MaterialTheme.typography.headlineMedium)
        Text(state.details?.description ?: "")

        Spacer(modifier = Modifier.height(24.dp))

        when (val install = state.installState) {
            is InstallState.NotInstalled -> {
                Button(onClick = { viewModel.onInstallClick() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Установить")
                }
            }
            is InstallState.Installing -> {
                Button(
                    onClick = { viewModel.onInstallClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Отменить (${install.progress}%)")
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { install.progress / 100f },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is InstallState.Installed -> {
                Button(onClick = {}, enabled = false, modifier = Modifier.fillMaxWidth()) {
                    Text("Установлено")
                }
            }
        }
    }
}