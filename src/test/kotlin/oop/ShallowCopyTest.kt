package oop

import collections.Product
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ShallowCopyTest {
    @Test
    internal fun `data copy function is shallow`() {
        val item1 = OrderItem(Product("baseball", 10.0), 5)
        val item2 = item1.copy()

        assertAll(
            { assertTrue(item1 == item2) },
            { assertFalse(item1 === item2) },
            { assertTrue(item1.product == item2.product) },
            { assertTrue(item1.product === item2.product) }
        )
    }
}