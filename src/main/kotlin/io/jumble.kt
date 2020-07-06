package io

import java.io.File

class Jumble {
    private val wordMap = File("/usr/share/dict/words").useLines { line ->
        line.filter { it.length == 5 || it.length == 6 }
                .groupBy(this::word2key)
    }

    private fun word2key(word: String) =
            word.toList().sorted().joinToString("")

    fun solve(clue: String): String = wordMap[word2key(clue)]?.get(0) ?: ""

    fun solveAll(vararg clues: String) =
            clues.map(this::solve)
}

fun main(args: Array<String>) {
    println(Jumble().solveAll(*args))
}