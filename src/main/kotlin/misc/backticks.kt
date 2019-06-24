package misc

fun `only use backtics on test functions`() {
    println("This works but is not a good idea")
}

fun underscores_are_also_okay_only_on_tests() {
    println("Again, please don't do this outside of tests")
}

fun main() {
    `only use backtics on test functions`()
    underscores_are_also_okay_only_on_tests()
}