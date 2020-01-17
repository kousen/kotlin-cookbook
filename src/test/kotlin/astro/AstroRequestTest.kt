package astro

import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Test

class AstroRequestTest {

    @Test
    fun `get people in space`() {
        val result = AstroRequest()()
        println(result)
        assertThat(result.message, `is`("success"))
        assertThat(result.number, `is`(greaterThanOrEqualTo(0)))
        assertThat(result.people.size, `is`(result.number))
    }

    @Test
    fun `ktor client`() = runBlocking {
        val result = ktorClient()
        println(result)
        assertThat(result.message, `is`("success"))
        assertThat(result.number, `is`(greaterThanOrEqualTo(0)))
        assertThat(result.people.size, `is`(result.number))
    }

    @Test
    fun `url with gson`() = runBlocking {
        val result = urlGson()
        println(result)
        assertThat(result.message, `is`("success"))
        assertThat(result.number, `is`(greaterThanOrEqualTo(0)))
        assertThat(result.people.size, `is`(result.number))
    }

}