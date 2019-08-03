package astro

import com.google.gson.Gson
import java.net.URL

class AstroRequest {
    companion object {
        private const val ASTRO_URL = "http://api.open-notify.org/astros.json"
    }

    operator fun invoke(): AstroResult =
        Gson().fromJson(URL(ASTRO_URL).readText(), AstroResult::class.java)
}

data class AstroResult(
    val message: String,
    val number: Int,
    val people: List<Assignment>
)

data class Assignment(
    val craft: String,
    val name: String
)

data class Astronaut(val name: String)

// NOTE: Does not use AstroRequest at all; see AstroRequestTest for that
fun main() {
    Gson().fromJson(
        URL("http://api.open-notify.org/astros.json").readText(),
        AstroResult::class.java
    ).also { astroResult ->
        println("There are ${astroResult.number} people in space:")
        astroResult.people.forEach { assignment ->
            println("\t${assignment.name} aboard ${assignment.craft}")
        }
    }.people.map { it.name }.also(::println)  // or use let instead of also
}

