package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val millis1 = measureTimeMillis {
        val job1 = launch {
            val response1 = networkCall(1)
            println(Thread.currentThread().name)
            println("Finished: response = $response1")
        }

        val job2 = launch {
            val response2 = networkCall(2)
            println(Thread.currentThread().name)
            println("Finished: response = $response2")
        }

        job1.join()
        job2.join()
    }

    println("Coroutines finished in ${millis1}ms")

    println(Thread.currentThread().name)
    println("Finished the main function")
}

suspend fun networkCall(n: Long) : String {
    delay(500)
    return "response $n"
}