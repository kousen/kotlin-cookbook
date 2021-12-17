package misc

fun main() {
    val list = listOf("a", "b", "c", "d", "e", "f", "g")
    val (a, b, c, d, e) = list
    println("$a $b $c $d $e")
    list.component1()

    val map = mapOf("a" to 1, "b" to 2, "c" to 2)
    for ((key, value) in map) {
        println("$key maps to $value")
    }
}