package vk.education.android.dev.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppDto(
    val id: String,
    val name: String,
    @SerialName("icon_url") val iconUrl: String? = null
)

@Serializable
data class AppDetailsDto(
    val id: String,
    val name: String,
    val description: String = "Описание отсутствует",
    val developer: String = "Неизвестно"
)