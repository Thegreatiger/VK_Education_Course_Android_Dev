package vk.education.android.dev.domain.usecase

import vk.education.android.dev.domain.model.App
import vk.education.android.dev.domain.repository.AppRepository

class GetAppsUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(): List<App> = repository.getApps()
}