package astro

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertAll

internal class OverheadTest {

    @Test
    fun getOverheadResponse() {
        // Portland, OR (sample response at http://open-notify.org/Open-Notify-API/)
        val latitude = 45.0
        val longitude = -122.3
        val altitude = 20

        val output = Overhead().getOverheadResponse(latitude, longitude, altitude)
        output.response.forEach {
            println(formatTimestamp(it.risetime))
        }
        assertAll(
            { assertEquals("success", output.message) },
            { assertEquals(45.0, output.request.latitude, 0.01) },
            { assertEquals(-122.3, output.request.longitude, 0.01) },
            { assertEquals(20.0, output.request.altitude, 0.01) },
            { assertEquals(5, output.request.passes) },
            { assertEquals(5, output.response.size) }
        )
    }
}