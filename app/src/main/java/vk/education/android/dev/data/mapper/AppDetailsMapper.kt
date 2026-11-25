package vk.education.android.dev.data.mapper

import vk.education.android.dev.data.local.AppDetailsEntity
import vk.education.android.dev.domain.AppDetails

class AppDetailsMapper {
    fun toDomain(entity: AppDetailsEntity): AppDetails = AppDetails(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        isInWishlist = entity.isInWishlist
    )

    fun toEntity(domain: AppDetails): AppDetailsEntity = AppDetailsEntity(
        id = domain.id,
        name = domain.name,
        description = domain.description,
        isInWishlist = domain.isInWishlist
    )
}