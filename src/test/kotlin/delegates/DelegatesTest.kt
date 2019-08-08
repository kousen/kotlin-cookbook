package delegates

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

internal class DelegatesTest {
    @Test
    internal fun `uninitialized value throws exception`() {
        assertThrows<IllegalStateException> { shouldNotBeNull }
    }

    @Test
    internal fun `initialize value then retrieve it`() {
        shouldNotBeNull = "Hello, World!"
        assertDoesNotThrow { shouldNotBeNull }
        assertEquals("Hello, World!", shouldNotBeNull)
    }

    @Test
    internal fun `watched variable prints old and new values`() {
        assertEquals(1, watched)
        watched *= 2
        assertEquals(2, watched)
        watched *= 2
        assertEquals(4, watched)
    }

    @Test
    internal fun `veto values less than zero`() {
        assertAll(
            { assertEquals(0, checked) },
            { checked = 42; assertEquals(42, checked) },
            { checked = -1; assertEquals(42, checked) },
            { checked = 17; assertEquals(17, checked) }
        )
    }

    @Test
    internal fun `lazy initialized value`() {
        assertEquals(42, ultimateAnswer)
        assertEquals(42, ultimateAnswer)
    }

    @Test
    fun `use map delegate for Project`() {
        val project = Project(
            mutableMapOf(
                "name" to "Learn Kotlin",
                "priorty" to 5,
                "completed" to true
            )
        )

        assertAll(
            { assertEquals("Learn Kotlin", project.name) },
            { assertEquals(5, project.priorty) },
            { assertTrue(project.completed) }
        )
    }

    @Test
    fun `use map from string`() {
        val project = Project(getMapFromJSON())
        assertAll(
            { assertEquals("Learn Kotlin", project.name) },
            { assertEquals(5, project.priorty) },
            { assertTrue(project.completed) }
        )
    }

    private fun getMapFromJSON() =
        Gson().fromJson<MutableMap<String, Any?>>(
            "{\"name\":\"Learn Kotlin\",\"priorty\":5,\"completed\":true}",
            MutableMap::class.java)
}