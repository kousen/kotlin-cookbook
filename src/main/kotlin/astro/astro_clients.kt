package astro

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

suspend fun urlKotlinxSerialization() = coroutineScope {
    withContext(Dispatchers.IO) {
        Json.decodeFromString<AstroResult>(
            URL("http://api.open-notify.org/astros.json").readText()
        )
    }
}

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
}

suspend fun main() {
    coroutineScope {
        val job1 = launch {
            urlGson().also { astroResult ->
                println("There are ${astroResult.number} people in space:")
                for ((name, craft) in astroResult.people) {
                    println("\t$name aboard $craft")
                }
            }
        }

        val job2 = launch {
            ktorClient().also { astroResult ->
                println("There are ${astroResult.number} people in space:")
                for ((name, craft) in astroResult.people) {
                    println("\t$name aboard $craft")
                }
            }
        }

        val job3 = launch {
            urlKotlinxSerialization().also { astroResult ->
                println("There are ${astroResult.number} people in space:")
                for ((name, craft) in astroResult.people) {
                    println("\t$name aboard $craft")
                }
            }
        }

        listOf(job1, job2, job3).joinAll()
    }
}
