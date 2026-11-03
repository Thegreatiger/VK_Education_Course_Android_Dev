package vk.education.android.dev.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import vk.education.android.dev.data.dto.AppDetailsDto
import vk.education.android.dev.data.dto.AppDto

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}