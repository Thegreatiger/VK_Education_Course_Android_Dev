package vk.education.android.dev

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import vk.education.android.dev.data.dto.AppDetailsDto
import vk.education.android.dev.data.dto.AppDto
import vk.education.android.dev.data.network.CatalogApi
import vk.education.android.dev.data.repository.AppRepositoryImpl

class AppRepositoryImplTest {

    private val api: CatalogApi = mockk()
    private val repository =
        AppRepositoryImpl(api)

    @Test
    fun `getApps returns mapped domain models`() = runTest {
        val mockDtoList = listOf(AppDto("1", "App1"), AppDto("2", "App2"))
        coEvery { api.getCatalog() } returns mockDtoList

        val result = repository.getApps()

        assertEquals(2, result.size)
        assertEquals("App1", result[0].name)
        coVerify { api.getCatalog() }
    }

    @Test
    fun `getApps propagates exception from api`() = runTest {
        coEvery { api.getCatalog() } throws RuntimeException("Network error")

        try {
            repository.getApps()
        } catch (e: Exception) {
            assertEquals("Network error", e.message)
        }
    }

    @Test
    fun `getAppDetails returns correct details`() = runTest {
        val mockDto = AppDetailsDto("1", "App1", "Desc")
        coEvery { api.getAppDetails("1") } returns mockDto

        val result = repository.getAppDetails("1")

        assertEquals("App1", result.name)
        assertEquals("Desc", result.description)
        coVerify { api.getAppDetails("1") }
    }

    @Test
    fun `getRating returns rating from api`() = runTest {
        coEvery { api.getRating("1") } returns 4.5f

        val result = repository.getRating("1")

        assertEquals(4.5f, result)
        coVerify { api.getRating("1") }
    }

    @Test
    fun `loadRatingsSequentially returns map of ratings`() = runTest {
        val ids = listOf("1", "2")
        coEvery { api.getRating("1") } returns 4.0f
        coEvery { api.getRating("2") } returns 5.0f

        val result = repository.loadRatingsSequentially(ids)

        assertEquals(2, result.size)
        assertEquals(4.0f, result["1"])
        assertEquals(5.0f, result["2"])
    }
}