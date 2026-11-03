package vk.education.android.dev.domain

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppDetails(id: String): AppDetails
}