package io

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalToCompressingWhiteSpace
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS

internal class FileIOTest {

    @Test
    fun `size of book_data csv file is 226`() {
        assertEquals(226, getSize("src/main/resources/book_data.csv"))
    }

    @Test @EnabledOnOs(OS.MAC)
    internal fun `10 longest words in dictionary`() {
        get10LongestWordsInDictionary().forEach { word -> println("$word (${word.length})") }
    }

    @Test @EnabledOnOs(OS.MAC)
    internal fun `group by length in dictionary`() {
        groupByLength().forEach(::println)
    }
}