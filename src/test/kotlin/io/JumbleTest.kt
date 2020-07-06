package io

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@Disabled("Dictionary not available on GitHub")
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

    @ParameterizedTest(name = "{0} unscrambles to {1}")
    @CsvSource("cautla, actual",
        "agileo, goalie", "mmlueb, mumble")
    fun `unscramble words`(clue: String, answer: String) =
        MatcherAssert.assertThat(jumble.solve(clue), Matchers.`is`(answer))

    @Test
    internal fun `check solveAll`() {
        assertEquals(listOf("actual", "goalie", "mumble"),
            jumble.solveAll("cautla", "agileo", "mmlueb"))
    }
}