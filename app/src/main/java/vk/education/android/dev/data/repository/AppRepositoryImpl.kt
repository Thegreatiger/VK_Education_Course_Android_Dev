package vk.education.android.dev.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import vk.education.android.dev.data.network.CatalogApi
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: CatalogApi
) : AppRepository {

    override suspend fun getApps(): List<App> {
        return api.getCatalog().map { App(it.id, it.name) }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        val dto = api.getAppDetails(id)
        return AppDetails(dto.id, dto.name, dto.description)
    }

    override suspend fun getRating(id: String): Float {
        return api.getRating(id)
    }

    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val result = mutableMapOf<String, Float>()
        for (id in ids) {
            result[id] = api.getRating(id)
        }
        return result
    }

    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        ids.map { id ->
            async { id to api.getRating(id) }
        }.awaitAll().toMap()
    }
}