package io

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class JumbleTest {
    private val jumble: Jumble = Jumble()

    @Test
    fun `check solver`() {
        assertAll(
                { assertEquals("actual", jumble.solve("cautla")) },
                { assertEquals("goalie", jumble.solve("agileo")) },
                { assertEquals("mumble", jumble.solve("mmlueb")) }
        )
    }

    @Test
    internal fun `check solveAll`() {
        assertEquals(listOf("actual", "goalie", "mumble"), jumble.solveAll("cautla", "agileo", "mmlueb"))
    }
}