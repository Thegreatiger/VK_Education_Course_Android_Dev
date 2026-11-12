package vk.education.android.dev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vk.education.android.dev.data.repository.AppRepositoryImpl
import vk.education.android.dev.domain.App
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppRepositoryImpl
) : ViewModel() {

    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps = _apps.asStateFlow()

    private val _timeLog = MutableStateFlow("")
    val timeLog = _timeLog.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = repository.getApps()
        }
    }

    fun loadRatingsSequentially() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            val time: Long = measureTimeMillis {
                val ratings = repository.loadRatingsSequentially(ids)
                updateRatings(ratings)
            }
            _timeLog.value = "Последовательно: $time мс"
        }
    }

    fun loadRatingsParallel() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            val time : Long = measureTimeMillis {
                val ratings = repository.loadRatingsInParallel(ids)
                updateRatings(ratings)
            }
            _timeLog.value = "Параллельно: $time мс"
        }
    }

    private fun updateRatings(ratings: Map<String, Float>) {
        _apps.update { currentList ->
            currentList.map { app ->
                app.copy(rating = ratings[app.id])
            }
        }
    }
}