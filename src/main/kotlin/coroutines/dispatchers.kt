package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking<Unit> {
    executorAsDispatcher()
    launchWithIO()
    launchWithDefault()
}

suspend fun executorAsDispatcher() {
    Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher().use {
            withContext(it) {
                delay(100L)
                println("Using provided thread pool")
                println(Thread.currentThread().name)
            }
        }
}

suspend fun launchWithIO() {
    withContext(Dispatchers.IO) {
        delay(100L)
        println("Using Dispatchers.IO")
        println(Thread.currentThread().name)
    }
}

suspend fun launchWithDefault() {
    withContext(Dispatchers.Default) {
        delay(100L)
        println("Using Dispatchers.Default")
        println(Thread.currentThread().name)
    }
}