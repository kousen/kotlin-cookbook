package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Suppress("USELESS_IS_CHECK")
class SequenceTests {
    @Test
    internal fun `create a sequence from values`() {
        val numSequence = sequenceOf(3, 1, 4, 1, 5, 9)
        assertTrue(numSequence is Sequence<Int>)
        assertEquals(6, numSequence.count())
    }

    @Test
    internal fun `create a sequence from a collection`() {
        val numSequence = listOf(3, 1, 4, 1, 5, 9).asSequence()
        assertTrue(numSequence is Sequence<Int>)
        assertEquals(6, numSequence.count())
    }
}