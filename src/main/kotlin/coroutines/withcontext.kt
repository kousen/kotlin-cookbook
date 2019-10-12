package coroutines

import kotlinx.coroutines.*

suspend fun fetchData() {
    val result1 = retrieve("www.mysite.com")
    val result2 = asyncRetrieve("www.mysite.com")
    println("printing result on ${Thread.currentThread().name} $result1")
    println("printing result on ${Thread.currentThread().name} $result2")
}

suspend fun retrieve(url: String) = withContext(Dispatchers.IO) {
    println("Retrieving data on ${Thread.currentThread().name}")
    delay(100L)
    "results"
}

suspend fun asyncRetrieve(url: String) = coroutineScope {
    // can replace async/await with withContext
    async(Dispatchers.IO) {
        println("Retrieving data on ${Thread.currentThread().name}")
        delay(100L)
        "asyncResults"
    }.await()
}

fun main() = runBlocking<Unit> {
    fetchData()
}