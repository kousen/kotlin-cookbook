package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RangeTests {
    @Test
    internal fun `coerceIn given a range`() {
        val range = 3..8

        assertThat(5, `is`(5.coerceIn(range)))
        assertThat(range.start, `is`(1.coerceIn(range)))
        assertThat(range.endInclusive, `is`(9.coerceIn(range)))
    }

    @Test
    internal fun `coerceIn given min and max`() {
        val min = 2
        val max = 6

        assertThat(5, `is`(5.coerceIn(min, max)))
        assertThat(min, `is`(1.coerceIn(min, max)))
        assertThat(max, `is`(9.coerceIn(min, max)))
    }

    @Test
    internal fun chunked() {
        val range = 0..10

        val chunked = range.chunked(3)
        assertThat(
            chunked, contains(
                listOf(0, 1, 2), listOf(3, 4, 5),
                listOf(6, 7, 8), listOf(9, 10)
            )
        )

        assertThat(range.chunked(3) { it.sum() }, `is`(listOf(3, 12, 21, 19)))
        assertThat(range.chunked(3) { it.average() }, `is`(listOf(1.0, 4.0, 7.0, 9.5)))
    }

    @Test
    internal fun windowed() {
        val range = 0..10

        assertAll("size = 3, step = 1",
            {
                assertThat(range.windowed(3, 1),
                    contains(
                        listOf(0, 1, 2), listOf(1, 2, 3), listOf(2, 3, 4),
                        listOf(3, 4, 5), listOf(4, 5, 6), listOf(5, 6, 7),
                        listOf(6, 7, 8), listOf(7, 8, 9), listOf(8, 9, 10)))
            },
            {
                assertThat(range.windowed(3, 1) { it.average() },
                    contains(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0))
            }
        )

        assertAll("size = 3, step = 3",
            {
                assertThat(range.windowed(3, 3),
                    contains(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8)))
            },
            {
                assertThat(
                    range.windowed(3, 3) { it.average() },
                    contains(1.0, 4.0, 7.0))
            })
    }
}