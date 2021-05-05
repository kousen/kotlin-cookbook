package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Before creating coroutine")
    runBlocking {
        print("Hello, ")
        delay(200L)
        println("World!")
    }
    println("After coroutine is finished")
}