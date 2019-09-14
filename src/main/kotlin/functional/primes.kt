package functional

import kotlin.math.ceil
import kotlin.math.sqrt

fun Int.isPrime() =
    this == 2 || (2..ceil(sqrt(this.toDouble())).toInt())
        .none { divisor -> this % divisor == 0 }

fun nextPrime(num: Int) =
    generateSequence(num + 1) { it + 1 }
        .first(Int::isPrime)

fun firstNPrimes(count: Int) =
    generateSequence(2, ::nextPrime)
        .take(count)
        .toList()

fun primesLessThan(max: Int): List<Int> =
//    generateSequence(2) { n -> if (n < max) nextPrime(n) else null }
//        .toList().dropLast(1)
    generateSequence(2, ::nextPrime)
        .takeWhile { it < max }
        .toList()


