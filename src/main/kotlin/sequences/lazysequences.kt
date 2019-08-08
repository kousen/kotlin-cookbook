package sequences

fun firstDoubleUsingCollectionsV1() =
    (100 until 200).map { println("doubling $it"); it * 2 }
        .filter { println("filtering $it"); it % 3 == 0 }
        .first()

fun firstDoubleUsingCollectionsV2() =
    (100 until 200).map { println("doubling $it"); it * 2 }
        .first { println("filtering $it"); it % 3 == 0 }

fun firstDoubleUsingSequences() =
    (100 until 2_000_000).asSequence()
        .map { println("doubling $it"); it * 2 }
        .filter { println("filtering $it"); it % 3 == 0 }
        .first()


fun main() {
//    println(firstDoubleUsingCollectionsV1())
//    println(firstDoubleUsingCollectionsV2())
    println(firstDoubleUsingSequences())
}