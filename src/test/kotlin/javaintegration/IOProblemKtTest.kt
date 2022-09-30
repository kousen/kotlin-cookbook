package javaintegration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException

internal class IOProblemKtTest {
    @Test
    internal fun `function throws an IOException`() {
        val exc = assertThrows<IOException> {
            houstonWeHaveAProblem()
        }
        assertThat(exc.message).isEqualTo("Nope")
    }
}