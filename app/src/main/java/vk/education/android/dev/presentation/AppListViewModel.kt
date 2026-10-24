package vk.education.android.dev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vk.education.android.dev.domain.model.App
import vk.education.android.dev.domain.usecase.GetAppsUseCase

class AppListViewModel(
    private val getAppsUseCase: GetAppsUseCase
) : ViewModel() {

    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps: StateFlow<List<App>> = _apps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = getAppsUseCase()
        }
    }
}