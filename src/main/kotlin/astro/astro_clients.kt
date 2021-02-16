@file:Suppress("HttpUrlsUsage")

package astro

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

fun urlKotlinxSerialization() =
    Json.decodeFromString<AstroResult>(
        URL("http://api.open-notify.org/astros.json").readText()
    )

fun urlGson(): AstroResult =
    Gson().fromJson(
        URL("http://api.open-notify.org/astros.json").readText(),
        AstroResult::class.java
    )


suspend fun ktorClient(): AstroResult {
    val client = HttpClient(CIO) {
        install(JsonFeature)
    }

    return client.get("http://api.open-notify.org/astros.json")
}

suspend fun main() {
    coroutineScope {
        val job1 = launch(Dispatchers.IO) {
            urlGson().also(::println)
        }

        val job2 = launch(Dispatchers.IO) {
            ktorClient().also(::println)
        }

        val job3 = launch(Dispatchers.IO) {
            urlKotlinxSerialization().also(::println)
        }

        listOf(job1, job2, job3).joinAll()
    }
}
