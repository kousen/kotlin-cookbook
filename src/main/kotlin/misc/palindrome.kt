package misc

import java.util.*

fun String.isPalindrome() =
    this.lowercase(Locale.getDefault())
        .replace("""[\W+]""".toRegex(), "")
        .let { it == it.reversed() }

fun isPal(string: String): Boolean {
    val testString = string.lowercase(Locale.getDefault())
        .replace("""[\W+]""".toRegex(), "")
    return testString == testString.reversed()
}
