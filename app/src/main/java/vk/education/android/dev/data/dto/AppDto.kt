package vk.education.android.dev.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppDto(val id: String, val name: String)

@Serializable
data class AppDetailsDto(val id: String, val name: String, val description: String = "Описание")