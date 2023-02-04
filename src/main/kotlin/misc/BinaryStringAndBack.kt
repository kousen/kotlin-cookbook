package misc

fun main() {
    val a = 12
    val bString = a.toString(radix = 2)
    println(bString)

    val i = bString.toInt(radix = 2)
    println(i)
}