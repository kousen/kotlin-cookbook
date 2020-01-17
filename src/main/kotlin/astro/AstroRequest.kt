package astro

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
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
    val name: String,
    val craft: String
)

suspend fun urlGson(): AstroResult = coroutineScope {
    withContext(Dispatchers.IO) {
        Gson().fromJson(
            URL("http://api.open-notify.org/astros.json").readText(),
            AstroResult::class.java
        )
    }
}

suspend fun ktorClient(): AstroResult = coroutineScope {
    val client = HttpClient() {
        install(JsonFeature)
    }

    client.get<AstroResult>("http://api.open-notify.org/astros.json")
        .also {
            client.close()
        }
}

fun main() {
    runBlocking {
        urlGson().let { astroResult ->
            println("There are ${astroResult.number} people in space:")
            for ((name, craft) in astroResult.people) {
                println("\t$name aboard $craft")
            }
        }

        ktorClient().let { astroResult ->
            println("There are ${astroResult.number} people in space:")
            for ((name, craft) in astroResult.people) {
                println("\t$name aboard $craft")
            }
        }
    }
}

