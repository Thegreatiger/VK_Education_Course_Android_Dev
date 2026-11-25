package vk.education.android.dev.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import vk.education.android.dev.data.local.AppDetailsDao
import vk.education.android.dev.data.mapper.AppDetailsMapper
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val existing = dao.getAppDetails(id).first()
        if (existing == null) {
            val newEntity = AppDetailsEntity(id, "Приложение $id", "Описание для $id", false)
            dao.insertAppDetails(newEntity)
            return mapper.toDomain(newEntity)
        }
        return mapper.toDomain(existing)
    }

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id).map { entity ->
            entity?.let { mapper.toDomain(it) } ?: AppDetails(id, "Загрузка...", "")
        }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.getAppDetails(id).first()
        currentEntity?.let {
            dao.updateWishlistStatus(id, !it.isInWishlist)
        }
    }
}