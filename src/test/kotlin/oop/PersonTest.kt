package oop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

data class Person(
    val first: String,
    val middle: String?,
    val last: String
)

@Suppress("USELESS_IS_CHECK", "UNUSED_VARIABLE", "UNUSED_VALUE")
class PersonTest {
    private val jkRowling = Person("Joanne", null, "Rowling")

    @Test
    fun `nullable middle name`() {
        assertNull(jkRowling.middle)
    }

    @Test
    fun `safe call operator`() {
        val p = Person(first = "North", middle = null, last = "West")
        var middleNameLength = p.middle?.length
        assertTrue(middleNameLength is Int?)

        if (p.middle != null) {
            middleNameLength = p.middle.length    // smart cast
        }
    }

    @Test
    fun `not-null assertion operator`() {
        @Suppress("CanBeVal")
        var p2 = Person(first = "North", middle = null, last = "West")
        if (p2.middle != null) {
            val middleNameLength = p2.middle!!.length  // ugh
        }
    }

    @Test
    internal fun `Elvis operator`() {
        val p = Person(first = "North", middle = null, last = "West")
        val middleNameLength = p.middle?.length ?: 0
    }

    @Test
    internal fun `safe cast`() {
        val person = jkRowling as? Person
        assertTrue(person is Person?)
    }
}