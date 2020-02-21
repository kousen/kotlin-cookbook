package collections

data class Golfer(val score: Int,
                  val first: String,
                  val last: String)

fun main() {
    val golfers = listOf(
        Golfer(70, "Jack", "Nicklaus"),
        Golfer(68, "Tom", "Watson"),
        Golfer(68, "Bubba", "Watson"),
        Golfer(70, "Tiger", "Woods"),
        Golfer(68, "Ty", "Webb")
    )

    // Create comparator and sort using it
    val comparator = compareBy<Golfer>(Golfer::score)
        .thenBy(Golfer::last)
        .thenBy(Golfer::first)
    golfers.sortedWith(comparator)
        .forEach(::println)

    // Sort by properties
    val sorted = golfers.sortedWith(
        compareBy({ it.score }, { it.last }, { it.first })
    )

    sorted.forEach { println(it) }
}
