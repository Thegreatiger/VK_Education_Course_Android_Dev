package vk.education.android.dev.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository
import vk.education.android.dev.domain.InstallState
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

data class AppDetailsUiState(
    val details: AppDetails? = null,
    val installState: InstallState = InstallState.NotInstalled
)

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String = savedStateHandle["appId"] ?: ""
    private val _state = MutableStateFlow(AppDetailsUiState())
    val state = _state.asStateFlow()

    private var installJob: Job? = null

    init {
        loadDetails()
    }

    private fun loadDetails() {
        viewModelScope.launch {
            val details = repository.getAppDetails(appId)
            _state.update { it.copy(details = details) }
        }
    }

    fun onInstallClick() {
        val currentState = _state.value.installState

        if (currentState is InstallState.Installing) {
            installJob?.cancel()
            _state.update { it.copy(installState = InstallState.NotInstalled) }
        } else {
            installJob = viewModelScope.launch {
                try {
                    emulateInstall()
                } catch (_: Exception) {
                }
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step 5) {
            _state.update { it.copy(installState = InstallState.Installing(progress)) }
            delay(150.milliseconds)
        }
        _state.update { it.copy(installState = InstallState.Installed) }
    }
}