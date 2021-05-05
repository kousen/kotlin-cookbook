package operators

import org.apache.commons.math3.complex.Complex

operator fun Complex.plus(c: Complex): Complex = this.add(c)
operator fun Complex.plus(d: Double): Complex = this.add(d)
operator fun Complex.minus(c: Complex): Complex = this.subtract(c)
operator fun Complex.minus(d: Double): Complex = this.subtract(d)
operator fun Complex.div(c: Complex): Complex = this.divide(c)
operator fun Complex.div(d: Double): Complex = this.divide(d)
operator fun Complex.times(c: Complex): Complex = this.multiply(c)
operator fun Complex.times(d: Double): Complex = this.multiply(d)
operator fun Complex.times(i: Int): Complex = this.multiply(i)
operator fun Double.times(c: Complex): Complex = c.multiply(this)
operator fun Complex.unaryMinus(): Complex = this.negate()


