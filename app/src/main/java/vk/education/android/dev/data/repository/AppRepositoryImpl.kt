package vk.education.android.dev.data.repository

import kotlinx.coroutines.delay
import vk.education.android.dev.data.dto.AppDto
import vk.education.android.dev.data.mapper.AppMapper
import vk.education.android.dev.domain.model.App
import vk.education.android.dev.domain.repository.AppRepository

class AppRepositoryImpl(
    private val mapper: AppMapper
) : AppRepository {
    override suspend fun getApps(): List<App> {
        delay(500) // Имитация загрузки из сети
        val hardcodedDtoList = listOf(
            AppDto("1", "VK", "Социальная сеть", "Соцсети"),
            AppDto("2", "RuStore", "Магазин приложений", "Инструменты"),
            AppDto("3", "VK Видео", "Смотрите видео", "Медиа"),
            AppDto("4", "VK Музыка", "Слушайте треки", "Медиа"),
            AppDto("5", "Дзен", "Новости и статьи", "Новости")
        )
        // Конвертируем DTO в Domain через Mapper
        return hardcodedDtoList.map { mapper.toDomain(it) }
    }
}