package astro

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AstroRequestTest {

    @Test
    fun `get people in space`() {
        checkAssertions(AstroRequest()())
    }

    @Test
    fun `ktor client`() = runBlocking<Unit> {
        checkAssertions(ktorClient())
    }

    @Test
    fun `url with gson`() = runBlocking<Unit> {
        checkAssertions(urlGson())
    }

    @Test
    fun `url with kotlinx serialization`() = runBlocking<Unit> {
        checkAssertions(urlKotlinxSerialization())
    }

    private fun checkAssertions(result: AstroResult) {
        println(result)
        assertAll(
            { assertEquals("success", result.message) },
            { assertTrue { result.number >= 0 } },
            { assertEquals(result.number, result.people.size) }
        )
    }

}