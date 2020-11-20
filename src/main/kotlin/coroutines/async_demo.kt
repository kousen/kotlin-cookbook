package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.random.Random

suspend fun addInts(x: Int, y: Int): Int {
    delay(Random.nextLong(1000L))
    return x + y
}

suspend fun main() = coroutineScope {
    val firstSum = async {
        println(Thread.currentThread().name)
        addInts(2, 2)
    }
    val secondSum = async {
        println(Thread.currentThread().name)
        addInts(3, 4)
    }
    println("Awaiting concurrent sums...")
    val total = firstSum.await() + secondSum.await()
    println("Total is $total")
}