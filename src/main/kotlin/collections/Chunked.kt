package collections

fun main(args: Array<String>) {
    //val list = ('a'..'z') + ('A'..'Z') + (0..9)

    val list = 0..10
    val pieces = list.chunked(3)
    println(pieces)

    val sums = list.chunked(3) { it.sum() }
    println(sums)

    println(list.windowed(3, 1))
    println(list.windowed(3, 1) { it.average() })
}
