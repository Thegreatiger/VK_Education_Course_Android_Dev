package vk.education.android.dev.domain

interface AppRepository {
    suspend fun getApps(): List<App>
}