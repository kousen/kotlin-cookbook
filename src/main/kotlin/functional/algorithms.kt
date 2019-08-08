package functional

import java.math.BigInteger

fun sum(vararg nums: Int) =
    nums.fold(0) { acc, n -> acc + n }

fun sumWithTrace(vararg nums: Int) =
    nums.fold(0) { acc, n ->
        println("acc = $acc, n = $n")
        acc + n
    }

fun sumReduce(vararg nums: Int) =
    nums.reduce { acc, i -> acc + i }

fun sumReduceDoubles(vararg nums: Int) =
    nums.reduce { acc, i ->
        println("acc=$acc, i=$i")
        acc + 2 * i
    }

@JvmOverloads
tailrec fun fibonacci(n: Int, a: Int = 0, b: Int = 1): Int =
    when (n) {
        0 -> a
        1 -> b
        else -> fibonacci(n - 1, b, a + b)
    }

fun fibonacciFold(n: Int) =
    (2 until n).fold(1 to 1) { (prev, curr), _ -> curr to (prev + curr) }.second


fun recursiveFactorial(n: Long): BigInteger =
    when (n) {
        0L, 1L -> BigInteger.ONE
        else -> BigInteger.valueOf(n) * recursiveFactorial(n - 1)
    }

fun factorialFold(n: Long): BigInteger =
    when (n) {
        0L, 1L -> BigInteger.ONE
        else -> (2..n).fold(BigInteger.ONE) { acc, i ->
            acc * BigInteger.valueOf(i)
        }
    }

@JvmOverloads
tailrec fun factorial(n: Long, acc: BigInteger = BigInteger.ONE): BigInteger =
    when (n) {
        0L -> BigInteger.ONE
        1L -> acc
        else -> factorial(n - 1, acc * BigInteger.valueOf(n))
    }

// fun BigInteger.length() = Math.log10(this.toDouble()).toLong() + 1