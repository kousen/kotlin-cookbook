package astro;

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.assertAll

import java.time.Instant;
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime;
import java.util.TimeZone;

class TimestampTest {

    @Test
    fun ts2zdt() {
        val timestamp: Long = 1576721287
        val zdt = ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            TimeZone.getDefault().toZoneId())
        assertAll(
            { assertEquals(Month.DECEMBER, zdt.month) },
            { assertEquals(18, zdt.dayOfMonth) },
            { assertEquals(2019, zdt.year) },
            { assertEquals(21, zdt.hour) },
            { assertEquals(8, zdt.minute) },
            { assertEquals(7, zdt.second) },
            { assertEquals(ZoneId.of("America/New_York"), zdt.zone) }
        )
    }

    @Test
    internal fun response() {
        val (message, iss_position, timestamp) = ProcessAstroData().response
        assertAll(
            { assertEquals("success", message) },
            { assertTrue(iss_position.latitude <= 90 &&
                iss_position.latitude >= -90) }
        )
        println(iss_position)
        println(timestamp)
    }
}
