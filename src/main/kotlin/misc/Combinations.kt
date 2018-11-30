package misc

fun <T> Iterable<T>.combinations() =
    this.flatMap { a ->
        this.map { b ->
            a to b
        }
    }

// TODO: Need an implementation of permutations() too

