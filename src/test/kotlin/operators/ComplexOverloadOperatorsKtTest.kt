package operators

import org.assertj.core.api.Assertions.assertThat
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.complex.Complex.*
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Math.*

internal class ComplexOverloadOperatorsKtTest {
    private val first = Complex(1.0, 3.0)
    private val second = Complex(2.0, 5.0)

    @Test
    internal fun plus() {
        val sum = first + second
        assertThat(sum).isEqualTo(Complex(3.0, 8.0))
    }

    @Test
    internal fun minus() {
        val diff = second - first
        assertThat(diff).isEqualTo(Complex(1.0, 2.0))
    }

    @Test
    internal fun negate() {
        val minus1 = -ONE

        assertThat(minus1.real).isCloseTo(-1.0, offset(0.000001))
        assertThat(minus1.imaginary).isCloseTo(0.0, offset(0.000001))
    }

    @Test
    internal fun `Euler's formula`() {
        val iPI = I * PI

        assertTrue(equals(iPI.exp(), -ONE, 0.000001))
    }
}