package serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Project(
    val name: String,
    val language: String
)

fun main() {
    val p = Project("Kotlin Cookbook", "Kotlin")
    Json.encodeToString(p)
        .also(::println)
}