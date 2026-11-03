package vk.education.android.dev.data.repository

import vk.education.android.dev.data.mapper.AppMapper
import vk.education.android.dev.data.network.CatalogApi
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: CatalogApi,
    private val mapper: AppMapper
) : AppRepository {

    override suspend fun getApps(): List<App> {
        return api.getCatalog().map { mapper.toDomain(it) }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        return mapper.toDomain(api.getAppDetails(id))
    }
}