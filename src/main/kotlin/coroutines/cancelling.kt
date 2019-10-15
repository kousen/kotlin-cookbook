package coroutines

import kotlinx.coroutines.*

suspend fun cancellable() =
    suspendCancellableCoroutine<Unit> { cont ->
        // do whatever, but if cancelled, use cont to resume
        println("Running a coroutine")
        cont.invokeOnCancellation {
            println("Coroutine was cancelled")
        }
    }

fun tiredOfWaiting() = runBlocking {
    val job: Job = launch {
        repeat(100) { i ->
            println("job: I'm waiting $i...")
            delay(100L)
        }
    }

    delay(500L)
    println("main: That's enough waiting")
    job.cancel()
    job.join()
    println("main: Done")
}

fun timeout() = runBlocking {
    withTimeout(1000L) {
        repeat(50) { i ->
            println("job: I'm waiting $i...")
            delay(100L)
        }
    }
}

fun main() {
    //tiredOfWaiting()
    timeout()
}