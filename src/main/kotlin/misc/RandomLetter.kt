package misc

fun main() {
    val c = 'a' + (26 * Math.random()).toInt()
    printLetterType(c)
    println(returnLetterType(c))
}

fun printLetterType(c: Char) {
    when (c) {
        'a', 'e', 'i', 'o', 'u' -> println("$c is a vowel")
        'y', 'w' -> println("$c is sometimes a vowel")
        else -> println("$c is a consonant")
    }
}

fun returnLetterType(c: Char) = when (c) {
    'a', 'e', 'i', 'o', 'u' -> "$c is a vowel"
    'y', 'w' -> "$c is sometimes a vowel"
    else -> "$c is a consonant"
}



