package vk.education.android.dev.data.mapper

import vk.education.android.dev.data.dto.AppDetailsDto
import vk.education.android.dev.data.dto.AppDto
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppDetails

class AppMapper {
    fun toDomain(dto: AppDto): App = App(
        id = dto.id,
        name = dto.name,
        iconUrl = dto.iconUrl
    )

    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        developer = dto.developer
    )
}