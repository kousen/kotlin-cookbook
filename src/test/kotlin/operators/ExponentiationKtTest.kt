package operators

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.math.pow

internal class ExponentiationKtTest {

    @Test
    internal fun `manually raise to a power`() {
        assertThat(256).isEqualTo(2.toDouble().pow(8).toInt())
    }

    @Test
    internal fun `raise to power`() {
        assertAll(
            { assertThat(1).isEqualTo(2 `**` 0) },
            { assertThat(2).isEqualTo(2 `**` 1) },
            { assertThat(4).isEqualTo(2 `**` 2) },
            { assertThat(8).isEqualTo(2 `**` 3) },

            { assertThat(1L).isEqualTo(2L `**` 0) },
            { assertThat(2L).isEqualTo(2L `**` 1) },
            { assertThat(4L).isEqualTo(2L `**` 2) },
            { assertThat(8L).isEqualTo(2L `**` 3) },

            { assertThat(1F).isEqualTo(2F `**` 0) },
            { assertThat(2F).isEqualTo(2F `**` 1) },
            { assertThat(4F).isEqualTo(2F `**` 2) },
            { assertThat(8F).isEqualTo(2F `**` 3) },

            { assertThat(1.0).isCloseTo(2.0 `**` 0, offset(1e-6))},
            { assertThat(2.0).isCloseTo(2.0 `**` 1, offset(1e-6))},
            { assertThat(4.0).isCloseTo(2.0 `**` 2, offset(1e-6))},
            { assertThat(8.0).isCloseTo(2.0 `**` 3, offset(1e-6))},

            { assertThat(1).isEqualTo(2.pow(0))},
            { assertThat(2).isEqualTo(2.pow(1))},
            { assertThat(4).isEqualTo(2.pow(2))},
            { assertThat(8).isEqualTo(2.pow(3))},

            { assertThat(1L).isEqualTo(2L.pow(0))},
            { assertThat(2L).isEqualTo(2L.pow(1))},
            { assertThat(4L).isEqualTo(2L.pow(2))},
            { assertThat(8L).isEqualTo(2L.pow(3))}
        )
    }
}