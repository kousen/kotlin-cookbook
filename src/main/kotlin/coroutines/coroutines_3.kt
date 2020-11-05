package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

val scope = CoroutineScope(Job())

suspend fun main() = coroutineScope {
    val elapsedTime = measureTimeMillis {
        val job = scope.launch {
            networkCall(1)
        }

        val job2 = scope.launch {
            networkCall(2)
        }

        val job3 = scope.launch {
            networkCall(3)
        }

        job.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                println("Coroutine 1 was cancelled")
            }
        }

        job2.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                println("Coroutine 2 was cancelled")
            }
        }

        job3.invokeOnCompletion { throwable ->
            if (throwable is CancellationException) {
                println("Coroutine 3 was cancelled")
            }
        }

        job.cancel()
        delay(1000)
        onDestroy()
        delay(1000)

    }

    println("Results after ${elapsedTime}ms")
}

fun onDestroy() {
    println("Cancelling scope")
    scope.cancel()
}