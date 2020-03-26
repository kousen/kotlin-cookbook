package coroutines

import kotlinx.coroutines.*
import java.net.URL
import kotlin.system.measureTimeMillis

fun downloadImage(url: String) = URL(url).readBytes()

fun downloadText(url: String) = URL(url).readText()

suspend fun downloadAll(urls: List<String>) = coroutineScope {
    urls.map {
        withContext(Dispatchers.IO) {
            async {
                // downloadImage(it)
                downloadText(it)
            }
        }
    }.awaitAll()
}

fun main() {
    runBlocking {
        measureTimeMillis {
            downloadAll(listOf("http://api.open-notify.org/astros.json",
                    "http://api.open-notify.org/astros.json",
                    "http://api.open-notify.org/astros.json",
                    "http://api.open-notify.org/astros.json",
                    "http://api.open-notify.org/astros.json"))
        }.also(::println)
    }
}