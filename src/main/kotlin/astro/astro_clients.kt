@file:Suppress("HttpUrlsUsage")

package astro

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import serialization.AstroResult
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

fun gsonResultOrError() =
    try {
        Success(urlGson())
    } catch (e: Exception) {
        Failure(e)
    }

suspend fun ktorClient(): AstroResult {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    return client.get("http://api.open-notify.org/astros.json").body()
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

        val job4 = launch {
            when (val result = gsonResultOrError()) {
                is Success<AstroResult> -> println(result.data)
                is Failure -> println("Exception: ${result.exception.message}")
            }
        }

        listOf(job1, job2, job3, job4).joinAll()
    }
}
