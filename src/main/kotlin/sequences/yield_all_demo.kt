package sequences

fun main() {
    val sequence = sequence {
        val start = 0
        yield(start)
        yieldAll(1..5 step 2)
        yieldAll(generateSequence(8) { it * 3 })
    }

    println(sequence.take(8).toList())
}