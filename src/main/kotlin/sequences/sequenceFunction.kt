package sequences

fun fibonacciSequence() = sequence {
    var terms = Pair(0, 1)

    while (true) {
        yield(terms.first)
        terms = terms.second to terms.first + terms.second
    }
}
