package operators

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.complex.Complex.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.closeTo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Math.*

internal class ComplexOverloadOperatorsKtTest {
    private val first = Complex(1.0, 3.0)
    private val second = Complex(2.0, 5.0)

    @Test
    internal fun plus() {
        val sum = first + second
        assertThat(sum, `is`(Complex(3.0, 8.0)))
    }

    @Test
    internal fun minus() {
        val diff = second - first
        assertThat(diff, `is`(Complex(1.0, 2.0)))
    }

    @Test
    internal fun negate() {
        val minus1 = -ONE

        assertThat(minus1.real, closeTo(-1.0, 0.000001))
        assertThat(minus1.imaginary, closeTo(0.0, 0.000001))
    }

    @Test
    internal fun `Euler's formula`() {
        val iPI = I * PI

        assertTrue(equals(iPI.exp(), -ONE, 0.000001))
    }
}