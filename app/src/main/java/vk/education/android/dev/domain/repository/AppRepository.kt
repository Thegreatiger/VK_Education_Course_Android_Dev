package vk.education.android.dev.domain.repository

import vk.education.android.dev.domain.model.App

interface AppRepository {
    suspend fun getApps(): List<App>
}