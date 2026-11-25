package vk.education.android.dev.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppDetailsRepository
import javax.inject.Inject

data class AppDetailsState(
    val isLoading: Boolean = true,
    val appDetails: AppDetails? = null,
    val error: String? = null
)

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String = savedStateHandle["appId"] ?: "1"

    private val _state = MutableStateFlow(AppDetailsState())
    val state = _state.asStateFlow()

    init {
        loadInitialData()
        observeAppDetails()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                repository.getAppDetails(appId)
                _state.value = _state.value.copy(isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            repository.observeAppDetails(appId)
                .collect { appDetails ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        appDetails = appDetails
                    )
                }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            repository.toggleWishlist(appId)
        }
    }
}