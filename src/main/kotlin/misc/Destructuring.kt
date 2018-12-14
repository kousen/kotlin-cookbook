package misc

fun main() {
    val list = listOf("a", "b", "c", "d", "e", "f", "g")
    val (a, b, c, d, e) = list
    println("$a $b $c $d $e")
    list.component1()
}