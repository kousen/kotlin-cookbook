package collections

import java.time.LocalDate
import kotlin.random.Random

fun main() {
    val n = Random.nextInt(from = 1, until = 10)
    if (n in 1..5) println(n)

    val startDate = LocalDate.now()
    val endDate = startDate.plusDays(5)

    val dateRange = startDate..endDate
    println(dateRange)
    println(LocalDate.now() in dateRange)
}