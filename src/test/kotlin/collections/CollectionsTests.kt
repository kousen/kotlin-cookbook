package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CollectionsTests {
    private val numList = listOf(3, 1, 4, 1, 5, 9)
            val mutableNums = mutableListOf(3, 1, 4, 1, 5, 9)
    private val numSet = setOf(3, 1, 4, 1, 5, 9)
    private val numMap = mapOf(3 to "three", 1 to "one", 4 to "four")

    @Nested
    inner class ListTests {
        @Test
        fun `toList on mutableList makes a readOnly new list`() {
            val readOnlyNumList = mutableNums.toList()
            assertEquals(mutableNums, readOnlyNumList)
            assertNotSame(mutableNums, readOnlyNumList)
        }

        @Test
        internal fun `list val is readOnly ref to same list`() {
            val readOnlySameList: List<Int> = mutableNums
            assertEquals(mutableNums, readOnlySameList)
            assertSame(mutableNums, readOnlySameList)
        }
    }

}