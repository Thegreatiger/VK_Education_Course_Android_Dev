package vk.education.android.dev.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    savedStateHandle: SavedStateHandle // Получаем ID из навигации
) : ViewModel() {

    private val appId: String = savedStateHandle["appId"] ?: ""

    private val _appDetails = MutableStateFlow<AppDetails?>(null)
    val appDetails: StateFlow<AppDetails?> = _appDetails.asStateFlow()

    init {
        loadDetails()
    }

    private fun loadDetails() {
        viewModelScope.launch {
            try {
                _appDetails.value = repository.getAppDetails(appId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}