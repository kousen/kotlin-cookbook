package astro

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Test

internal class AstroRequestTest {
    val request = AstroRequest()

    @Test
    internal fun `get people in space`() {
        val result = request()
        println(result)
        assertThat(result.message, `is`("success"))
        assertThat(result.number, `is`(greaterThanOrEqualTo(0)))
        assertThat(result.people.size, `is`(result.number))
    }
}