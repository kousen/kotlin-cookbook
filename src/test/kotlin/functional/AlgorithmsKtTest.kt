package functional

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger
import java.util.stream.Stream

@Suppress("unused")
internal class AlgorithmsKtTest {
    private val fibData = listOf(
        1 to 1, 2 to 1, 3 to 2,
        4 to 3, 5 to 5, 6 to 8,
        7 to 13, 8 to 21, 9 to 34,
        10 to 55)

    companion object {
        // needed if parameterized test done with Lifecycle.PER_METHOD
        @JvmStatic
        fun fibs() = listOf(
            Arguments.of(1, 1), Arguments.of(2, 1),
            Arguments.of(3, 2), Arguments.of(4, 3),
            Arguments.of(5, 5), Arguments.of(6, 8),
            Arguments.of(7, 13), Arguments.of(8, 21),
            Arguments.of(9, 34), Arguments.of(10, 55))
    }

    private fun fibnumbers() = listOf(
        Arguments.of(1, 1), Arguments.of(2, 1),
        Arguments.of(3, 2), Arguments.of(4, 3),
        Arguments.of(5, 5), Arguments.of(6, 8),
        Arguments.of(7, 13), Arguments.of(8, 21),
        Arguments.of(9, 34), Arguments.of(10, 55))

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

    @ParameterizedTest(name = "fibonacci({0}) == {1}")
    @MethodSource("fibs")
    fun `first 10 Fibonacci numbers (companion method)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))

    @ParameterizedTest(name = "fibonacci({0}) == {1}")
    @MethodSource("fibnumbers")
    fun `first 10 Fibonacci numbers (instance method)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))

    @ParameterizedTest
    @CsvSource("1, 1", "2, 1", "3, 2",
        "4, 3", "5, 5", "6, 8", "7, 13",
        "8, 21", "9, 34", "10, 55")
    fun `first 10 Fibonacci numbers (csv)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))

    @ParameterizedTest
    @MethodSource("fibonacciTestData")
    fun `check fibonacci using data class`(data: FibonacciTestData) {
        assertThat(fibonacci(data.number), `is`(data.expected))
    }

    private fun fibonacciTestData() = Stream.of(
        FibonacciTestData(number = 1, expected = 1),
        FibonacciTestData(number = 2, expected = 1),
        FibonacciTestData(number = 3, expected = 2),
        FibonacciTestData(number = 4, expected = 3),
        FibonacciTestData(number = 5, expected = 5),
        FibonacciTestData(number = 6, expected = 8),
        FibonacciTestData(number = 7, expected = 13)
    )

    @ParameterizedTest
    @MethodSource("fibonacciTestData")
    fun `check fibonacci with fold`(data: FibonacciTestData) {
        assertThat(fibonacciFold(data.number), `is`(data.expected))
    }

    @Test
    fun `watch fibonacci fold in action`() {
        val total = fibonacciFoldDebug(10)
        println("Fib(10) == $total")
    }

    @Test @Disabled("slow")
    internal fun `factorial tests`() {
        assertAll(
            { assertThat(factorial(0), `is`(BigInteger.ONE)) },
            { assertThat(factorial(1), `is`(BigInteger.ONE)) },
            { assertThat(factorial(2), `is`(BigInteger.valueOf(2))) },
            { assertThat(factorial(5), `is`(BigInteger.valueOf(120))) },
            { assertThat(factorial(15000).toString().length, `is`(56130)) },
            { assertThat(factorial(75000).toString().length, `is`(333061)) }
        )
    }

    @Test
    internal fun `check recursive factorial`() {
        assertAll(
            { assertThat(recursiveFactorial(0), `is`(BigInteger.ONE)) },
            { assertThat(recursiveFactorial(1), `is`(BigInteger.ONE)) },
            { assertThat(recursiveFactorial(2), `is`(BigInteger.valueOf(2))) },
            { assertThat(recursiveFactorial(5), `is`(BigInteger.valueOf(120))) },
            { assertThrows<StackOverflowError> { recursiveFactorial(8000) }}
        )
    }

    @Test @Disabled("slow")
    internal fun `folding factorial tests`() {
        assertAll(
            { assertThat(factorialFold(0), `is`(BigInteger.ONE)) },
            { assertThat(factorialFold(1), `is`(BigInteger.ONE)) },
            { assertThat(factorialFold(2), `is`(BigInteger.valueOf(2))) },
            { assertThat(factorialFold(5), `is`(BigInteger.valueOf(120))) },
            { assertThat(factorialFold(15000).toString().length, `is`(56130)) },
            { assertThat(factorialFold(75000).toString().length, `is`(333061)) }
        )
    }

    @Test
    internal fun `sum using fold`() {
        val numbers = intArrayOf(3, 1, 4, 1, 5, 9)
        assertEquals(numbers.sum(), sum(*numbers))
    }

    @Test
    internal fun `sum using fold with trace`() {
        val numbers = intArrayOf(3, 1, 4, 1, 5, 9)
        assertEquals(numbers.sum(), sumWithTrace(*numbers))
    }

    @Test
    internal fun `sum using reduce`() {
        val numbers = intArrayOf(3, 1, 4, 1, 5, 9)
        assertAll(
            { assertEquals(numbers.sum(), sumReduce(*numbers)) },
            { assertThrows<UnsupportedOperationException> { sumReduce() }}
        )
    }

    @Test
    internal fun `sum using reduce doubles`() {
        val numbers = intArrayOf(3, 1, 4, 1, 5, 9)
        // first value is not doubled!
        assertEquals(numbers.sum() * 2 - 3, sumReduceDoubles(*numbers))
    }
}

data class FibonacciTestData(val number: Int, val expected: Int)
