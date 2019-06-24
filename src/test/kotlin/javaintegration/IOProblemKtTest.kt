package javaintegration

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException

internal class IOProblemKtTest {
    @Test
    internal fun `function throws an IOException`() {
        val exc = assertThrows<IOException> {
            houstonWeHaveAProblem()
        }
        assertThat(exc.message, `is`("Nope"))
    }
}