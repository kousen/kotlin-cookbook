package collections

fun main(args: Array<String>) {
    val range = 3..8
    println(5.coerceIn(range))
    println(1.coerceIn(range))
    println(9.coerceIn(range))

    println(5.coerceIn(2, 6))
    println(1.coerceIn(2, 6))
    println(9.coerceIn(2, 6))
}