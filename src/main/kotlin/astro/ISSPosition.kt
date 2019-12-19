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

data class Response(
    val message: String,
    val iss_position: IssPosition,
    val timestamp: Long
) {

    fun getZDT(): ZonedDateTime =
        ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            TimeZone.getDefault().toZoneId()
        )
}

class ProcessAstroData {
    companion object {
        const val url = "http://api.open-notify.org/iss-now.json"
        val gson = Gson()
    }

    val response
        get() = gson.fromJson(URL(url).readText(), Response::class.java)

    fun getPosition() =
        response.also {
            println(
                "As of " + it.getZDT().format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                )
            )
        }.iss_position
}

fun main() {
    val demo = ProcessAstroData()
    val (lat, lng) = demo.getPosition()
    println("$lat deg N, $lng deg W")
}