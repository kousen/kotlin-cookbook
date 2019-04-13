package functional

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger

@Suppress("unused")
internal class AlgorithmsKtTest {
    private val fibData = listOf(
        1 to 1, 2 to 1, 3 to 2,
        4 to 3, 5 to 5, 6 to 8,
        7 to 13, 8 to 21, 9 to 34,
        10 to 55)

    companion object {
        @JvmStatic
        fun fibs() = listOf(
            Arguments.of(1, 1), Arguments.of(2, 1),
            Arguments.of(3, 2), Arguments.of(4, 3),
            Arguments.of(5, 5), Arguments.of(6, 8),
            Arguments.of(7, 13), Arguments.of(8, 21),
            Arguments.of(9, 34), Arguments.of(10, 55))
    }

    @ParameterizedTest(name = "fibonacci({0}) == {1}")
    @MethodSource("fibs")
    fun `first 10 Fibonacci numbers (method)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))

    @ParameterizedTest
    @CsvSource("1, 1", "2, 1", "3, 2",
        "4, 3", "5, 5", "6, 8", "7, 13",
        "8, 21", "9, 34", "10, 55")
    fun `first 10 Fibonacci numbers (csv)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))

    @Test
    internal fun `Fibonacci numbers (explicit)`() {
        assertAll(
            { assertThat(fibonacci(4), `is`(3)) },
            { assertThat(fibonacci(9), `is`(34)) },
            { assertThat(fibonacci(2000), `is`(1392522469)) }
        )
    }

    @TestFactory
    fun `first 10 Fibonacci numbers (dynamic)`() =
        fibData.map { (n, fib) ->
            DynamicTest.dynamicTest("fibonacci($n) == $fib") {
                assertThat(fibonacci(n), `is`(fib))
            }
        }

    @Test
    internal fun `factorial tests`() {
        assertAll(
            { assertThat(factorial(0), `is`(BigInteger.ONE)) },
            { assertThat(factorial(1), `is`(BigInteger.ONE)) },
            { assertThat(factorial(2), `is`(BigInteger.valueOf(2L))) },
            { assertThat(factorial(5), `is`(BigInteger.valueOf(120L))) },
            { assertThat(factorial(15000).toString().length, `is`(56130)) },
            { assertThat(factorial(75000).toString().length, `is`(333061)) }
        )
    }
}