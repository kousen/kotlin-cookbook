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

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson(json, T::class.java)
    // this.fromJson(json, typeOf<T>().javaType)

fun main() = Gson().fromJson<AstroData>(
    URL("http://api.open-notify.org/astros.json").readText()
).let {
    println("There are ${it.number} people in space")
    for ((n, c) in it.people) {
        println("$n aboard $c")
    }
}