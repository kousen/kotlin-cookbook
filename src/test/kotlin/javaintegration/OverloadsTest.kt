package javaintegration

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll

internal class OverloadsTest {

    @Test
    internal fun `check all overloads`() {
        assertAll("Overloads called from Kotlin",
            { println(addProduct("Name", 5.0, "Desc")) },
            { println(addProduct("Name", 5.0)) },
            { println(addProduct("Name")) }
        )
    }

    @Test
    internal fun `check overloaded Product constructor`() {
        assertAll("Overloads called from Kotlin",
            { println(Product("Name", 5.0, "Desc")) },
            { println(Product("Name", 5.0)) },
            { println(Product("Name")) }
        )
    }
}