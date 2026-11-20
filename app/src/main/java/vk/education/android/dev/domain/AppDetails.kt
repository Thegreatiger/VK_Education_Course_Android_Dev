package vk.education.android.dev.domain

data class AppDetails(
    val id: String,
    val name: String,
    val description: String
)

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}