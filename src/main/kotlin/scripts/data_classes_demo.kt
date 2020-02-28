package scripts

data class KotlinPerson(val first: String, val last: String)

fun main() {
    val p1 = KotlinPerson("Jean-Luc", "Picard")
    val p2 = KotlinPerson(first = "Jean-Luc", last = "Picard")
    println(p1); println(p2)
    println(p1 == p2) // delegates to equals method
    println(p1 === p2) // reference equality

    val fred = KotlinPerson("Fred", "Flintstone")
    val wilma = fred.copy(first = "Wilma")
    println(fred)
    println(wilma)

    val map = mapOf("a" to 1, "b" to 2, "c" to 2)
    for ((k,v) in map) {
        println("$k maps to $v")
    }
}