package astro

import com.google.gson.Gson
import java.net.URL
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

data class OverheadResponse(
    val message: String,
    val request: OverheadRequest,
    val response: ArrayList<TimeAndDuration>
)

data class OverheadRequest(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val passes: Int,
    val datetime: Long
)

data class TimeAndDuration(
    val risetime: Long,
    val duration: Long
)

const val base = "http://api.open-notify.org/iss-pass.json"

fun formatTimestamp(timestamp: Long): String =
    ZonedDateTime.ofInstant(
        Instant.ofEpochSecond(timestamp),
        TimeZone.getDefault().toZoneId()
    ).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG))

fun main() {
    // Marlborough, CT
//    val latitude = 41.6314
//    val longitude = -72.4596

    // Portland, OR (sample response at http://open-notify.org/Open-Notify-API/)
    val latitude = 45.0
    val longitude = -122.3

    val json = URL("$base?lat=$latitude&lon=$longitude").readText()
    val output = Gson().fromJson(json, OverheadResponse::class.java)
    output.response.forEach {
        println(formatTimestamp(it.risetime))
    }
}