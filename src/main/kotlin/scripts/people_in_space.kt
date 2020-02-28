package scripts

import com.google.gson.Gson
import java.net.URL

data class NameCraft(
    val name: String,
    val craft: String
)

data class AstroData(
    val message: String,
    val number: Int,
    val people: List<NameCraft>
)

fun main() {
    val data = Gson().fromJson(
        URL("http://api.open-notify.org/astros.json").readText(),
        AstroData::class.java)
    println("There are ${data.number} people in space")
    for ((n,c) in data.people) {
        println("$n aboard $c")
    }
}