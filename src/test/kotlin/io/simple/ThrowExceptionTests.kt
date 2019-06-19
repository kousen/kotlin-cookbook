package io.simple

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.closeTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ThrowExceptionTests {

    @Test
    fun divideWithoutException() {
        assertThat(2.0, closeTo(ThrowException.divide(4, 2), 0.000001))
    }

    @Test
    fun divideWithException() {
        assertThrows<ArithmeticException> { ThrowException.divide(4, 0) }
    }
}