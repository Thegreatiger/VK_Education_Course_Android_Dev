package vk.education.android.dev.domain.usecase

import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository

class GetAppDetailsUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(id: String): AppDetails = repository.getAppDetails(id)
}