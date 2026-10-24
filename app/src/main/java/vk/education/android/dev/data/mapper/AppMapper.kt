package vk.education.android.dev.data.mapper

import vk.education.android.dev.data.dto.AppDto
import vk.education.android.dev.domain.model.App

class AppMapper {
    fun toDomain(dto: AppDto): App = App(
        id = dto.id,
        name = dto.appName,
        description = dto.desc,
        category = dto.cat
    )
}