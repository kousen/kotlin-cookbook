package coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis

suspend fun main() {                                // A function that can be suspended and resumed later
    val start = currentTimeMillis()
    coroutineScope {                                // Create a scope for starting coroutines
        for (i in 1..10) {
            launch {                                // Start 10 concurrent tasks
                delay(3000L - i * 300)              // Pause their execution
                log(start, "Countdown: $i")
            }
        }
    }
    // Execution continues when all coroutines in the scope have finished
    log(start, "Liftoff!")
}

fun log(start: Long, msg: String) {
    println(
        "$msg " +
                "(on ${Thread.currentThread().name}) " +
                "after ${(currentTimeMillis() - start) / 1000F}s"
    )
}