package oop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CustomerTest {
    @Test
    internal fun `load messages`() {
        val customer = Customer("Fred").apply { messages }
        assertEquals(3, customer.messages.size)
    }

    @Test
    internal fun `check reference equality`() {
        val c1 = Customer("Buffy")
        val c2 = Customer("Buffy")
        val c3 = c2

        assertAll(
            { assertFalse(c1 === c2) },
            { assertTrue(c2 === c3) })
    }

    @Test
    internal fun `check equivalence`() {
        val c1 = Customer("Buffy")
        val c2 = Customer("Buffy")
        val c3 = Customer("Willow")

        assertAll(
            { assertTrue(c1 == c2) },
            { assertFalse(c1 == c3) })
    }
}