package simple

import com.google.gson.Gson
import java.net.URL

data class AstroAssignment(val name: String,
                           val craft: String)

data class AstroResult(val message: String,
                       val number: Int,
                       val people: List<AstroAssignment>)

class Astro {
    companion object {
        val url = "http://api.open-notify.org/astros.json"
    }

    fun getAstronauts() {
        val astroResult = Gson().fromJson(URL(url).readText(), AstroResult::class.java)
        println("There are ${astroResult.number} people in space")
    }
}

fun main() {
    Astro().getAstronauts()
}