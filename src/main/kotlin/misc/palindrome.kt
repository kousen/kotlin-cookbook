package misc

fun String.isPalindrome() =
    this.toLowerCase().replace("""[\W+]""".toRegex(), "")
        .let { it == it.reversed() }

fun isPal(string: String): Boolean {
    val testString = string.toLowerCase()
        .replace("""[\W+]""".toRegex(), "")
    return testString == testString.reversed()
}
