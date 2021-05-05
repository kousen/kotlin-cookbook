package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val deferred1 = async {
        networkCall(1)
    }

    val deferred2 = async {
        networkCall(2)
    }

    val elapsedTime = measureTimeMillis {
        awaitAll(deferred1, deferred2).also(::println)
        awaitAll(deferred2, deferred1).also(::println)
//        deferred1.await().also(::println)
//        deferred2.await().also(::println)
    }

    println("The results finished after ${elapsedTime}ms")
}

