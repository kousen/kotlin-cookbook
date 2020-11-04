package misc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

enum class Directions {
    NORTH, SOUTH, EAST, WEST
}

class EnumTest {
    @Test
    internal fun `convert a string name into an enum instance`() {
        val enumValue = Directions.valueOf("NORTH")
        assertEquals(Directions.NORTH, enumValue)
    }

    @Test
    internal fun `get name and ordinal from enum`() {
        assertAll(
            { assertEquals("NORTH", Directions.NORTH.name) },
            { assertEquals(0, Directions.NORTH.ordinal) },
            { assertEquals(1, Directions.SOUTH.ordinal) },
            { assertEquals(2, Directions.EAST.ordinal) },
            { assertEquals(3, Directions.WEST.ordinal) },
        )
    }
}