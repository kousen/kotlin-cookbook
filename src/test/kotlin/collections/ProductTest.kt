package collections

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ProductTest {
    @Test
    internal fun `check equivalence`() {
        val p1 = Product("baseball", 10.0)
        val p2 = Product("baseball", 10.0, false)

        assertEquals(p1, p2)
        assertEquals(p1.hashCode(), p2.hashCode())
    }

    @Test
    internal fun `create set to check equals and hashcode`() {
        val p1 = Product("baseball", 10.0)
        val p2 = Product(price = 10.0, onSale = false, name = "baseball")

        val products = setOf(p1, p2)
        assertEquals(1, products.size)
    }

    @Test
    internal fun `check generated toString function`() {
        val p = Product("baseball", 10.0)

        assertEquals(
            "Product(name=baseball, price=10.0, onSale=false)",
            p.toString()
        )
    }

    @Test
    internal fun `change price using copy`() {
        val p1 = Product("baseball", 10.0)
        val p2 = p1.copy(price = 12.0)
        assertAll(
            { assertEquals("baseball", p2.name) },
            { assertThat(p2.price).isCloseTo(12.0, offset(0.01)) },
            { assertFalse(p2.onSale) }
        )
    }

    @Test
    internal fun `destructure using component functions`() {
        val p = Product("baseball", 10.0)

        val (name, price, sale) = p
        assertAll(
            { assertEquals(p.name, name) },
            { assertThat(p.price).isCloseTo(price, offset(0.01)) },
            { assertFalse(sale) })
    }
}