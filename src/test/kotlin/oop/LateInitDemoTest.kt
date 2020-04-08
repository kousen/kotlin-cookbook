package oop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LateInitDemoTest {

    @Test
    internal fun `uninitialized lateinit property throws exception`() {
        assertThrows<UninitializedPropertyAccessException> { LateInitDemo().name }
    }

    @Test
    internal fun `set the lateinit property and no exception is thrown`() {
        assertDoesNotThrow { LateInitDemo().apply { name = "Dolly" } }
    }
}