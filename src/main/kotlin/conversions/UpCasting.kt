package conversions

fun main() {
    val intVar: Int = 3
    // val longVar: Long = intVar // Does not compile
    val longVar: Long = intVar.toLong()
    println("$intVar, $longVar")

    val longSum = 3L + intVar
    println(longSum)
}