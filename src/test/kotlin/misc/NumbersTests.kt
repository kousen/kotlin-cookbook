package misc

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test

class NumbersTests {
    @Test
    internal fun toBinaryStringAndBack() {
        val str = 42.toString(radix = 2)
        assertThat(str, `is`("101010"))

        val num = "101010".toInt(radix = 2)
        assertThat(num, `is`(42))
    }

    @Test
    internal fun paddedBinaryString() {
        val strings = (0..15).map {
            it.toString(2).padStart(4, '0')
        }

        assertThat(strings, contains(
            "0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011",
            "1100", "1101", "1110", "1111"))

        val nums = strings.map { it.toInt(2) }
        assertThat(nums, contains(
            0, 1, 2, 3,
            4, 5, 6, 7,
            8, 9, 10, 11,
            12, 13, 14, 15))
    }

    @Test
    internal fun `famous saying`() {
        val joke = """
    There are ${3.toString(3)} kinds of developers:
        - Those who know binary,
        - Those who don't, and
        - Those who didn't realize this is actually a ternary joke"""

        println(joke)
    }
}