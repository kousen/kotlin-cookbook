package scripts

import functional.sum

val add = { x: Int, y: Int -> x + y }

fun addNums(vararg ints: Int) = sum(*ints)

fun main() {
    assert(add(2, 3) == 5)
    assert(addNums(1, 2, 3, 4, 5) == 16)
}