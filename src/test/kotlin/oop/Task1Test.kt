package oop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class Task1Test {
    @Test
    internal fun `initialize with apply`() {
        val task = Task1("task").apply { priority = 4 }
        assertAll(
            { assertEquals(4, task.priority) },
            { assertFalse(task.lowPriority) }
        )
    }
}