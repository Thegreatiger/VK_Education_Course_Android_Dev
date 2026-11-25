package vk.education.android.dev.domain

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails
    fun observeAppDetails(id: String): Flow<AppDetails>
    suspend fun toggleWishlist(id: String)
}