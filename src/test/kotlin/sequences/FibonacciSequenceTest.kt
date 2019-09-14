package sequences

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FibonacciSequenceTest {

    @Test
    internal fun `first 10 Fibonacci numbers from sequence`() {
        val fibs = fibonacciSequence()
            .take(10)
            .toList()

        assertEquals(listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34), fibs)
    }
}