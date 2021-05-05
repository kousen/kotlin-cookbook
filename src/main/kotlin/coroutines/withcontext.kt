@file:Suppress("UNUSED_PARAMETER")
package coroutines

import kotlinx.coroutines.*

suspend fun retrieve1(url: String) =
    withContext(Dispatchers.IO + CoroutineName("withContext")) {
    println("Retrieving data on ${Thread.currentThread().name}")
    delay(100L)
    "withContextResults"
}

suspend fun retrieve2(url: String) = coroutineScope {
    // can replace async/await with withContext
    async(Dispatchers.IO + CoroutineName("async")) {
        println("Retrieving data on ${Thread.currentThread().name}")
        delay(100L)
        "asyncResults"
    }.await()
}

fun main() = runBlocking {
    val result1 = retrieve1("www.mysite.com")
    val result2 = retrieve2("www.mysite.com")
    println("printing result on ${Thread.currentThread().name} $result1")
    println("printing result on ${Thread.currentThread().name} $result2")
}