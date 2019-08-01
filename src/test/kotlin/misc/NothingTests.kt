@file:Suppress("USELESS_IS_CHECK")

package misc

import org.junit.Ignore
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class NothingTests {
    @Test
    internal fun `nullable val with no type is Nothing?`() {
        val x = null
        assertTrue(x is Nothing?)
    }

    private fun doNothing(): Nothing = throw Exception("Nothing at all")

    @Test
    internal fun `function throws exception`() {
        val ex = assertThrows<Exception> { doNothing() }
        assertTrue(ex is Exception)
    }

    @Suppress("UNUSED_VARIABLE")
    @Test @Ignore("randomly throws exception (on purpose)")
    internal fun `if with exception`() {
        val x: String = if (Random.nextBoolean()) "true" else throw Exception("nope")
    }

    @Test
    internal fun `remainder mod 3`() {
        for (n in 1..10) {
            val x = when (n % 3) {
                0 -> "$n % 3 == 0"
                1 -> "$n % 3 == 1"
                2 -> "$n % 3 == 2"
                else -> "Houston, we have a problem..."
            }
            assertFalse(x == "Houston, we have a problem")
        }
    }
}