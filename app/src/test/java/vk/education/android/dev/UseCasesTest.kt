package vk.education.android.dev

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import vk.education.android.dev.domain.App
import vk.education.android.dev.domain.AppDetails
import vk.education.android.dev.domain.AppRepository
import vk.education.android.dev.domain.usecase.GetAppDetailsUseCase
import vk.education.android.dev.domain.usecase.GetAppsUseCase

class UseCasesTest {

    private val repository: AppRepository = mockk()

    @Test
    fun `GetAppsUseCase invokes repository getApps`() = runTest {
        val mockApps = listOf(App("1", "Test"))
        coEvery { repository.getApps() } returns mockApps

        val useCase = GetAppsUseCase(repository)
        val result = useCase()

        assertEquals(mockApps, result)
        coVerify(exactly = 1) { repository.getApps() }
    }

    @Test
    fun `GetAppsUseCase propagates exception`() = runTest {
        coEvery { repository.getApps() } throws RuntimeException("DB Error")
        val useCase = GetAppsUseCase(repository)

        try {
            useCase()
        } catch (e: Exception) {
            assertEquals("DB Error", e.message)
        }
    }

    @Test
    fun `GetAppDetailsUseCase invokes repository with correct id`() = runTest {
        val mockDetails = AppDetails("1", "Name", "Desc")
        coEvery { repository.getAppDetails("1") } returns mockDetails

        val useCase = GetAppDetailsUseCase(repository)
        val result = useCase("1")

        assertEquals(mockDetails, result)
        coVerify(exactly = 1) { repository.getAppDetails("1") }
    }

    @Test
    fun `GetAppDetailsUseCase returns empty description if none provided`() = runTest {
        val mockDetails = AppDetails("2", "Name2", "")
        coEvery { repository.getAppDetails("2") } returns mockDetails

        val useCase = GetAppDetailsUseCase(repository)
        val result = useCase("2")

        assertEquals("", result.description)
    }

    @Test
    fun `GetAppDetailsUseCase propagates exception if app not found`() = runTest {
        coEvery { repository.getAppDetails("3") } throws NoSuchElementException("Not found")
        val useCase = GetAppDetailsUseCase(repository)

        try {
            useCase("3")
        } catch (e: Exception) {
            assertEquals("Not found", e.message)
        }
    }
}