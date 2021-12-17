package extension

// Extension property
val Int.isEven: Boolean
    get() = this % 2 == 0

fun main() {
    for (i in 1..10) {
        println("$i is ${if(i.isEven) "even" else "odd"}")
    }
}