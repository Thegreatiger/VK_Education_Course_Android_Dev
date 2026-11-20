package vk.education.android.dev
import org.junit.Assert.assertEquals
import org.junit.Test
import vk.education.android.dev.data.dto.AppDetailsDto
import vk.education.android.dev.data.dto.AppDto
import vk.education.android.dev.data.mapper.AppMapper

class AppMapperTest {

    private val mapper = AppMapper()

    @Test
    fun `map AppDto to App returns correct id and name`() {
        val dto = AppDto("1", "Test App")
        val result = mapper.toDomain(dto)
        assertEquals("1", result.id)
        assertEquals("Test App", result.name)
    }

    @Test
    fun `map AppDto with empty name returns App with empty name`() {
        val dto = AppDto("2", "")
        val result = mapper.toDomain(dto)
        assertEquals("", result.name)
    }

    @Test
    fun `map AppDetailsDto to AppDetails returns correct data`() {
        val dto = AppDetailsDto("3", "Details App", "Cool description")
        val result = mapper.toDomain(dto)
        assertEquals("3", result.id)
        assertEquals("Details App", result.name)
        assertEquals("Cool description", result.description)
    }

    @Test
    fun `map AppDetailsDto with default description returns default text`() {
        val dto = AppDetailsDto("4", "App No Desc")
        val result = mapper.toDomain(dto)
        assertEquals("Описание", result.description)
    }

    @Test
    fun `map AppDetailsDto to AppDetails preserves all fields`() {
        val dto = AppDetailsDto("5", "Full App", "Full Desc")
        val result = mapper.toDomain(dto)
        assertEquals(dto.id, result.id)
        assertEquals(dto.name, result.name)
        assertEquals(dto.description, result.description)
    }
}