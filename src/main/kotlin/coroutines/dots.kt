package coroutines

import kotlinx.coroutines.*

// From https://kotlinlang.org/docs/coroutines-basics.html#coroutines-are-light-weight
fun main() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}
