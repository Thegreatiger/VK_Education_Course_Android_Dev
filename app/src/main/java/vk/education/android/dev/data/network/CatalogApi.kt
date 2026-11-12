package vk.education.android.dev.data.network

import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import vk.education.android.dev.data.dto.AppDetailsDto
import vk.education.android.dev.data.dto.AppDto
import kotlin.time.Duration.Companion.milliseconds

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

    suspend fun getRating(id: String): Float {
        delay(1500.milliseconds)
        return (30..50).random() / 10f
    }
}