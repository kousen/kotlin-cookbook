package coroutines

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.closeTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.*

class OpenWeatherMapTests {
    private val owm = OpenWeatherMap()
    private val model = owm.getWeather()

    @Test
    internal fun `convert from Kelvin to F`() {
        assertAll(
            { assertThat(model.convertTemp(273.15), `is`(closeTo( 32.0, 0.01))) },
            { assertThat(model.convertTemp(373.15), `is`(closeTo(212.0, 0.01))) }
        )
    }

    @Test
    internal fun `convert from meters per sec to miles per hour`() {
        assertThat(model.convertSpeed(1.0), `is`(closeTo(2.23694, 0.00001)))
    }

    @Test
    internal fun `Is it always sunny in Philadelphia?`() {
        println(owm.getWeather("19102"))
    }
}