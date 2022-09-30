package coroutines

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class OpenWeatherMapTests {
    private val owm = OpenWeatherMap()
    private val model = owm.getWeatherByZip()

    @Test
    internal fun `convert from Kelvin to F`() {
        assertAll(
            { assertThat(model.convertTemp(273.15)).isCloseTo( 32.0, offset(0.01)) },
            { assertThat(model.convertTemp(373.15)).isCloseTo(212.0, offset(0.01)) }
        )
    }

    @Test
    internal fun `convert from meters per sec to miles per hour`() {
        assertThat(model.convertSpeed(1.0)).isCloseTo(2.23694, offset(0.00001))
    }

    @Test
    internal fun `Is it always sunny in Philadelphia`() {
        println(owm.getWeatherByZip("19102"))
    }
}