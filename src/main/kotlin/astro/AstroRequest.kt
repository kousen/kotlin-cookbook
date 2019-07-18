package astro

import com.google.gson.Gson
import java.net.URL

class AstroRequest {
    companion object {
        private const val ASTRO_URL = "http://api.open-notify.org/astros.json"
    }

    operator fun invoke(): AstroResult {
        val responseString = URL(ASTRO_URL).readText()
        return Gson().fromJson(responseString, AstroResult::class.java)
    }
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

// NOTE: Does not use AstroRequest at all; see AstroRequestTest for that
fun main() {
    val result = Gson().fromJson(
        URL("http://api.open-notify.org/astros.json").readText(),
        AstroResult::class.java)
    println("There are ${result.number} people in space:")
    result.people.forEach { astronaut ->
        println("\t${astronaut.name} aboard ${astronaut.craft}")
    }
}

