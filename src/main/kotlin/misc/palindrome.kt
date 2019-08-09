package misc

fun String.isPalindrome(): Boolean =
    this.toLowerCase().replace("""[\W+]""".toRegex(), "")
        .let { it == it.reversed() }
