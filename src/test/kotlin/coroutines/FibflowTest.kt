package coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class FibflowTest {
    @ExperimentalCoroutinesApi
    @Test
    internal fun `first 10 Fibonacci numbers from sequence`() {
        val fibs = runBlocking<List<BigInteger>> {
            fibflow().take(10)
                .toList()
        }

        //assertEquals(listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34), fibs)
        assertThat(
            fibs, contains(
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger("2"),
                BigInteger("3"),
                BigInteger("5"),
                BigInteger("8"),
                BigInteger("13"),
                BigInteger("21"),
                BigInteger("34")
            )
        )
    }

}