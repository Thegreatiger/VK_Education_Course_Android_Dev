package vk.education.android.dev
import kotlinx.serialization.Serializable

@Serializable
object AppListDestination

@Serializable
data class AppDetailsDestination(
    val name: String,
    val description: String
)