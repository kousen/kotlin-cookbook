package misc

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CombinationsKtTest {

    @Test
    fun combinations() {
        val nums = listOf(1, 2, 3)
        val combinations = nums.combinations()
        assertThat(combinations, containsInAnyOrder(
            1 to 1, 1 to 2, 1 to 3,
            2 to 1, 2 to 2, 2 to 3,
            3 to 1, 3 to 2, 3 to 3))
    }
}