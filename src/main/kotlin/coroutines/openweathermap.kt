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

    fun simpleString(): String =
        """
            $name
                Current: ${NumberFormat.getInstance().format(temperature)} F
                High: $high F, Low: $low F
        """.trimIndent()

    override fun toString(): String {
        val df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val nf = NumberFormat.getNumberInstance()

        return """
            For ${name}, as of ${df.format(time)}:
            Description  : ${weather[0].description}
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
        const val BASE = "https://api.openweathermap.org/data/2.5/weather"
    }

    val APPID = java.lang.System.getenv("OPENWEATHERMAP_API_KEY")

    private val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting().create()

    fun getWeatherByZip(zip: String = "06447"): Model {
        val url = "$BASE?zip=${zip.substring(0..4)}&appid=$APPID"
        val text = URL(url).readText()
        return gson.fromJson(text, Model::class.java)
    }

    fun getWeatherByCity(city: String = "London"): Model {
        val url = "$BASE?q=${city}&appid=$APPID"
        val text = URL(url).readText()
        return gson.fromJson(text, Model::class.java)
    }
}

fun syncZips(vararg zips: String): List<Model> {
    val owm = OpenWeatherMap()
    return zips.map { owm.getWeatherByZip(it) }
}

suspend fun asyncZips(vararg zips: String) = coroutineScope {
    val owm = OpenWeatherMap()
    withContext(Dispatchers.IO) {
        zips.map {
            async {
                owm.getWeatherByZip(it)
            }
        }.awaitAll()
    }
}

suspend fun asyncCities(vararg cities: String) = coroutineScope {
    val owm = OpenWeatherMap()
    withContext(Dispatchers.IO) {
        cities.map { city ->
            async { owm.getWeatherByCity(city) }
        }.awaitAll()
    }
}

inline fun <R> measureTimeAndReturn(block: () -> R): Pair<Long, R> {
    val start = java.lang.System.currentTimeMillis()
    val result = block()
    val time = java.lang.System.currentTimeMillis() - start
    return Pair(time, result)
}

fun main() = runBlocking<Unit> {
    // synchronous
    val (time, resultList) = measureTimeAndReturn {
        syncZips("06447", "96801", "02115")
    }
    println("Elapsed time (sync): ${time}ms")
    resultList.forEach { println(it.simpleString()) }

    println()
    // asynchronous
    measureTimeAndReturn {
        asyncZips("06447", "96801", "02115")
    }.also { (time, results) ->
        println("Elapsed time (async): ${time}ms")
        results.forEach { result -> println(result.simpleString()) }
    }

    println()
    // By cities
    measureTimeAndReturn {
        asyncCities("London", "Hyderabad", "San Francisco")
    }.also { (time, results) ->
        println("Elapsed time (async): ${time}ms")
        results.forEach { result -> println(result.simpleString()) }
    }
}
