package operators

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.closeTo
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll
import kotlin.math.pow

internal class ExponentiationKtTest {

    @Test
    internal fun `manually raise to a power`() {
        assertThat(256, equalTo(2.toDouble().pow(8).toInt()))
    }

    @Test
    internal fun `raise to power`() {
        assertAll(
            { assertThat(1, equalTo(2 `**` 0))},
            { assertThat(2, equalTo(2 `**` 1))},
            { assertThat(4, equalTo(2 `**` 2))},
            { assertThat(8, equalTo(2 `**` 3))},

            { assertThat(1L, equalTo(2L `**` 0))},
            { assertThat(2L, equalTo(2L `**` 1))},
            { assertThat(4L, equalTo(2L `**` 2))},
            { assertThat(8L, equalTo(2L `**` 3))},

            { assertThat(1F, equalTo(2F `**` 0))},
            { assertThat(2F, equalTo(2F `**` 1))},
            { assertThat(4F, equalTo(2F `**` 2))},
            { assertThat(8F, equalTo(2F `**` 3))},

            { assertThat(1.0, closeTo(2.0 `**` 0, 1e-6))},
            { assertThat(2.0, closeTo(2.0 `**` 1, 1e-6))},
            { assertThat(4.0, closeTo(2.0 `**` 2, 1e-6))},
            { assertThat(8.0, closeTo(2.0 `**` 3, 1e-6))},

            { assertThat(1, equalTo(2.pow(0)))},
            { assertThat(2, equalTo(2.pow(1)))},
            { assertThat(4, equalTo(2.pow(2)))},
            { assertThat(8, equalTo(2.pow(3)))},

            { assertThat(1L, equalTo(2L.pow(0)))},
            { assertThat(2L, equalTo(2L.pow(1)))},
            { assertThat(4L, equalTo(2L.pow(2)))},
            { assertThat(8L, equalTo(2L.pow(3)))}
        )
    }
}