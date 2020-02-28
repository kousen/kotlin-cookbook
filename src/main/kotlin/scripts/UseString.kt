package scripts

fun printLength(s: String?): String =
    "The length of $s is ${s?.length}"

fun main() {
    println(printLength("hello"))
    println(printLength(""))
    println(printLength(null))
}