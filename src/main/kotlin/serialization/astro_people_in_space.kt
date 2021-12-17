package serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

@Serializable
data class Assignment(
    val name: String,
    val craft: String
)

@Serializable
data class AstroResult(
    val message: String,
    val number: Int,
    val people: List<Assignment>
)

fun deserializeFromURL() =
    Json.decodeFromString<AstroResult>(
        URL("http://api.open-notify.org/astros.json").readText()
    )