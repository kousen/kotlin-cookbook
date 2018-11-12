package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.not
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
        fun `toList on mutableList makes a new readOnly list`() {
            val readOnlyNumList: List<Int> = mutableNums.toList()
            assertEquals(mutableNums, readOnlyNumList)
            assertNotSame(mutableNums, readOnlyNumList)
        }

        @Test
        internal fun `modify mutable list does not change read-only list`() {
            val readOnly = mutableNums.toList()
            assertEquals(mutableNums, readOnly)

            mutableNums.add(2)
            assertThat(readOnly, not(contains(2)))
        }

        @Test
        internal fun `read-only view of a mutable list`() {
            val readOnlySameList: List<Int> = mutableNums
            assertEquals(mutableNums, readOnlySameList)
            assertSame(mutableNums, readOnlySameList)

            mutableNums.add(2)
            assertEquals(mutableNums, readOnlySameList)
            assertSame(mutableNums, readOnlySameList)
        }
    }

}