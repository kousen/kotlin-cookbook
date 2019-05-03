package io

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FileIOTest {

    @Test
    fun `size of book_data csv file is 226`() {
        assertEquals(226, getSize("src/scripts.main/resources/book_data.csv"))
    }

    @Test
    internal fun `10 longest words in dictionary`() {
        get10LongestWordsInDictionary().forEach { word -> println("$word (${word.length})") }
    }

    @Test
    internal fun `group by length in dictionary`() {
        groupByLength().forEach(::println)
    }
}