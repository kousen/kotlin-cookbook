package io.simple

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ThrowExceptionTests {

    @Test
    fun divideWithoutException() {
        assertThat(2.0).isCloseTo(ThrowException.divide(4, 2), offset(0.000001))
    }

    @Test
    fun divideWithException() {
        assertThrows<ArithmeticException> { ThrowException.divide(4, 0) }
    }
}