package collections

fun main(args: Array<String>) {
    val list = 0..10
    val pieces = list.chunked(3)
    println(pieces)

    val sums = list.chunked(3) { it.sum() }
    println("Sums: $sums")

    println(list.windowed(3, 1))
    println("Averages: " + list.windowed(3, 1) { it.average() })

    println(list.windowed(3, 3))
    println("Averages: " + list.windowed(3, 3) { it.average() })
}
