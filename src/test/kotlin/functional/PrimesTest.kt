package functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.system.measureTimeMillis

internal class PrimesTest {
    @ParameterizedTest(name = "{0} is prime and less than 20")
    @ValueSource(ints = [2, 3, 5, 7, 11, 13, 17, 19])
    internal fun `primes less than 20`(n: Int) {
        assertTrue(n.isPrime())
    }

    @ParameterizedTest(name = "{0} is composite and less than or equal to 20")
    @ValueSource(ints = [4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20])
    internal fun `composites less than or equal to 20`(n: Int) {
        assertFalse(n.isPrime())
    }

    @ParameterizedTest
    @MethodSource("nextPrimeTestData")
    fun `check isPrime values using data class`(data: NextPrimeTestData) {
        assertEquals(data.expected, nextPrime(data.number))
    }

    private fun nextPrimeTestData() = listOf(
        NextPrimeTestData(2, 3),
        NextPrimeTestData(3, 5),
        NextPrimeTestData(4, 5),
        NextPrimeTestData(5, 7),
        NextPrimeTestData(6, 7),
        NextPrimeTestData(7, 11),
        NextPrimeTestData(182, 191),
        NextPrimeTestData(9973, 10007)
    )

    @Test
    internal fun `first 10 primes`() {
        val primes = firstNPrimes(10)
            // .also(::println)
        assertEquals(10, primes.size)
        assertEquals(listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29), primes)
    }

    @Test
    internal fun `first 1000 primes`() {
        val time = measureTimeMillis {
            val primes = firstNPrimes(10_000)
            println("${primes.size} prime numbers generated (the last one is ${primes.last()})")
        }
        println("Algorithm took ${time}ms")
    }

    @Test
    internal fun `find all primes less than 20`() {
        primesLessThan(20)
            .also(::println)
    }
}

data class NextPrimeTestData(val number: Int, val expected: Int)