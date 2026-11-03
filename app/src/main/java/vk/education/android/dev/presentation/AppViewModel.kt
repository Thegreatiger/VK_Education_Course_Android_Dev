package vk.education.android.dev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppRepository
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    // Зависимость внедряется сюда через Hilt автоматически!
    private val repository: AppRepository
) : ViewModel() {

    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps: StateFlow<List<App>> = _apps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = repository.getApps()
        }
    }
}