package io

import java.io.File

fun getSize(fileName: String) = File(fileName).length()

fun get10LongestWordsInDictionary() =
    File("/usr/share/dict/words").useLines { line ->
        line.filter { it.length > 20 }
            .sortedByDescending(String::length)
            .take(10)
            .toList()
    }

fun groupByLength() =
    File("/usr/share/dict/words").useLines {
        it.groupingBy(String::length)
            .eachCount()
            .toSortedMap()
    }

fun replaceText(fileName: String, data: String) =
        File(fileName).writeText(data)

fun printData(fileName: String, data: String) =
        File(fileName).printWriter().use { writer ->
            writer.println(data)
        }




