package oop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TaskTests {
    @Test
    internal fun `priority below min is min`() {
        val task = Task("task", Task.MIN_PRIORITY - 1)
        assertEquals(Task.MIN_PRIORITY, task.priority)
    }

    @Test
    internal fun `priority above max is max`() {
        val task = Task("task", Task.MAX_PRIORITY + 1)
        assertEquals(Task.MAX_PRIORITY, task.priority)
    }

    @Test
    internal fun `change priority above max`() {
        val task = Task("task")

        assertEquals(Task.DEFAULT_PRIORITY, task.priority)

        task.priority = Task.MAX_PRIORITY + 1
        assertEquals(Task.MAX_PRIORITY, task.priority)
    }

    @Test
    internal fun `change priority below min`() {
        val task = Task("task")

        assertEquals(Task.DEFAULT_PRIORITY, task.priority)

        task.priority = Task.MIN_PRIORITY - 1
        assertEquals(Task.MIN_PRIORITY, task.priority)
    }

    @Test
    internal fun `priorities between min and max are as given`() {
        val task = Task("task")
        (Task.MIN_PRIORITY..Task.MAX_PRIORITY).forEach { p ->
            task.priority = p
            assertEquals(p, task.priority)
        }
    }
}