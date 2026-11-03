package vk.education.android.dev.data

import kotlinx.coroutines.delay
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppRepository
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class AppRepositoryImpl @Inject constructor() : AppRepository {
    override suspend fun getApps(): List<App> {
        delay(500.milliseconds)
        return listOf(
            App("1", "VK"),
            App("2", "RuStore"),
            App("3", "VK Видео")
        )
    }
}