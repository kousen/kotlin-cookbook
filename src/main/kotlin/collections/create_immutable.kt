package collections

fun main(args: Array<String>) {
    val set = mutableSetOf(1, 3, 2)
    val immutableSet: Set<Int> = set

    println(set)
    println(immutableSet)
    println(set == immutableSet)
    println(set === immutableSet)

    val set1 = set.toSet()
    println(set == set1)
    println(set === set1)
    println(set::class)
    println(set1::class)
}