package misc

import java.util.stream.IntStream

import kotlin.system.measureTimeMillis

fun doubleIt(x: Int): Int {
    Thread.sleep(100L)
    println("doubling $x with on thread ${Thread.currentThread().name}")
    return x * 2
}

fun main() {
    println("This machine has ${Runtime.getRuntime().availableProcessors()} processors")

    var time = measureTimeMillis {
        IntStream.rangeClosed(1, 8)
            .map { doubleIt(it) }
            .sum()
    }
    println("Sequential stream took ${time}ms")

    time = measureTimeMillis {
        IntStream.rangeClosed(1, 8)
            .parallel()
            .map { doubleIt(it) }
            .sum()
    }
    println("Parallel stream took ${time}ms")

}