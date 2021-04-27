package astro

import com.google.gson.Gson
import java.net.URL
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

data class IssPosition(
    val latitude: Double,
    val longitude: Double
)

data class IssResponse(
    val message: String,
    val iss_position: IssPosition,
    val timestamp: Long
) {
    val zdt: ZonedDateTime
        get() = ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            TimeZone.getDefault().toZoneId()
        )
}

class ProcessAstroData {
    companion object {
        const val url = "http://api.open-notify.org/iss-now.json"
    }

    val response: IssResponse
        get() = Gson().fromJson(URL(url).readText(), IssResponse::class.java)

    val position
        get() = response.also {
            println(
                "As of " + it.zdt.format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                )
            )
        }.iss_position
}

fun main() {
    val demo = ProcessAstroData()
    val (lat, lng) = demo.position
    println("$lat deg N, $lng deg W")
}