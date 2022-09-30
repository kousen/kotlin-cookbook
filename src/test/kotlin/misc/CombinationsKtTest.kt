package misc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CombinationsKtTest {

    @Test
    fun combinations() {
        val nums = listOf(1, 2, 3)
        val combinations = nums.combinations()
        assertThat(combinations).contains(
            1 to 1, 1 to 2, 1 to 3,
            2 to 1, 2 to 2, 2 to 3,
            3 to 1, 3 to 2, 3 to 3)
    }
}