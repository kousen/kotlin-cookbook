package serialization

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AstroPeopleInSpaceKtTest {
    @Test
    fun `kotlinx deserialization`() {
        val astroResult = deserializeFromURL()
        println(astroResult)
        assertAll(
            { assertTrue(astroResult.number >= 0) },
            { assertEquals(astroResult.number, astroResult.people.size)}
        )
    }
}