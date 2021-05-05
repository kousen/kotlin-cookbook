package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Suppress("USELESS_IS_CHECK")
class ArrayTests {
    @Test
    internal fun `create with arrayOf`() {
        val strings = arrayOf("this", "is", "an", "array", "of", "strings")
        assertTrue(strings is Array<String>)
    }

    @Test
    internal fun `array of nulls`() {
        val nullStringArray = arrayOfNulls<String>(5)
        for (x in nullStringArray) {
            assertNull(x)
        }
    }

    @Test
    internal fun `valid indices`() {
        val strings = arrayOf("this", "is", "an", "array", "of", "strings")
        val indices = strings.indices
        assertThat(indices, contains(0, 1, 2, 3, 4, 5))
    }

    @Test
    internal fun `withIndex returns IndexValues`() {
        val strings = arrayOf("this", "is", "an", "array", "of", "strings")
        for ((index, value) in strings.withIndex()) {
            println("Index $index maps to $value")
            assertTrue(index in 0..5)
        }
    }

    @Test
    internal fun `constructor with function`() {
        val squares = Array(5) { i -> (i * i).toString() }
        assertThat(squares, `is`(arrayContaining("0", "1", "4", "9", "16")))
    }
}