package coroutines

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import java.net.URL
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.system.measureTimeMillis

data class Clouds(
    val all: Double
)

data class Wind(
    val speed: Double,
    val deg: Double
)

data class System(
    val message: String,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Coordinates(
    val lat: Double,
    val lon: Double
)

data class Main(
    val temp: Double,
    val humidity: Double,
    val pressure: Double,
    val tempMin: Double,
    val tempMax: Double
)

data class Model(
    val dt: Long,
    val id: Long,
    val name: String,
    val cod: Int,
    val coord: Coordinates,
    val main: Main,
    val sys: System,
    val wind: Wind,
    val clouds: Clouds,
    val weather: List<Weather>
) {
    // Convert from Kelvin to Fahrenheit
    fun convertTemp(temp: Double) =
        9 * (temp - 273.15) / 5 + 32

    fun convertTime(time: Long): LocalDateTime =
        Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault()).toLocalDateTime()

    // 1 m/sec * 60 sec/min * 60 min/hr * 100 cm/m * 1 in/2.54 cm
    //      * 1 ft/12 in * 1 mi/5280 ft
    fun convertSpeed(mps: Double) =
        mps * 60 * 60 * 100 / 2.54 / 12 / 5280

    private val time: LocalDateTime
        get() = convertTime(dt)

    private val temperature
        get() = convertTemp(main.temp)

    private val low
        get() = floor(convertTemp(main.tempMin))

    private val high
        get() = ceil(convertTemp(main.tempMax))

    private val sunrise: LocalDateTime
        get() = convertTime(sys.sunrise)

    private val sunset: LocalDateTime
        get() = convertTime(sys.sunset)

    private val speed
        get() = convertSpeed(wind.speed)

    override fun toString(): String {
        val df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val nf = NumberFormat.getNumberInstance()

        return """
            For ${name}, as of ${df.format(time)}:
            Icon         : http://openweathermap.org/img/w/${weather[0].icon}.png
            Current Temp : ${nf.format(temperature)} F (high: $high F, low: $low F)
            Humidity     : ${main.humidity}%
            Sunrise      : ${df.format(sunrise)}
            Sunset       : ${df.format(sunset)}
            Wind         : ${nf.format(speed)} mph at ${wind.deg} deg
            Cloudiness   : ${clouds.all}%
        """.trimIndent()
    }
}

class OpenWeatherMap {
    companion object {
        const val base = "http://api.openweathermap.org/data/2.5/weather"
        const val appid = "d82ee6ea026dd986ea1e975d14875060"
    }

    private val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting().create()

    fun getWeather(zip: String = "06447"): Model {
        val url = "$base?zip=${zip.substring(0..4)}&appid=$appid"
        val text = URL(url).readText()
        // val json = JsonParser.parseString(text)
        // println(gson.toJson(json))
        return gson.fromJson(text, Model::class.java)
    }
}

suspend fun getWeatherForZip(owm: OpenWeatherMap, zip: String) =
    withContext(Dispatchers.IO) {
        owm.getWeather(zip)
    }


suspend fun main() {
    val owm = OpenWeatherMap()
    val zips = listOf("06447", "96801", "02115")
    // synchronous
    measureTimeMillis {
        zips.forEach {
            owm.getWeather(it)
        }
    }.also {
        println("Elapsed time (synchronous) : $it ms")
    }

    // asynchronous
    measureTimeMillis {
        coroutineScope {
            zips.map { zip ->
                launch {
                    println(getWeatherForZip(owm, zip))
                }
            }
        }
    }.also {
        println("Elapsed time (asynchronous): $it ms")
    }
}