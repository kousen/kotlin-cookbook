package misc

val <T> T.exhaustive: T
    get() = this

fun printMod3(n: Int) {
    when (n % 3) {
        0 -> println("$n % 3 == 0")
        1 -> println("$n % 3 == 1")
        2 -> println("$n % 3 == 2")
    }
}

fun printMod3SingleStatement(n: Int) = when (n % 3) {
    0 -> println("$n % 3 == 0")
    1 -> println("$n % 3 == 1")
    2 -> println("$n % 3 == 2")
    else -> println("Houston, we have a problem...")
}

fun printMod3Exhaustive(n: Int) {
    when (n % 3) {
        0 -> println("$n % 3 == 0")
        1 -> println("$n % 3 == 1")
        2 -> println("$n % 3 == 2")
        else -> println("Houston, we have a problem...")
    }.exhaustive
}


fun main() {
    (1..10).forEach { printMod3(it) }
    (1..10).forEach { printMod3SingleStatement(it) }
    (1..10).forEach { printMod3Exhaustive(it) }
}