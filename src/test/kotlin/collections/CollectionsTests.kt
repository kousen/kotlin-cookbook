package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.UnsupportedOperationException
import kotlin.collections.LinkedHashSet

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

        @Test
        internal fun `current implementation class of listOf is Arrays$ArrayList`() {
            assertEquals("class java.util.Arrays\$ArrayList", numList::class.java.toString())
        }

        @Test
        internal fun `numList is unmodifiable using add`() {
            numList as MutableList
            assertThrows<UnsupportedOperationException> { numList.add(42) }
        }

        @Test
        internal fun `numList is unmodifiable using set`() {
            numList as MutableList
            assertDoesNotThrow { numList.set(0, 42) }
        }
    }

    @Nested
    inner class SetTests {
        @Test
        internal fun `setOf creates mutable set`() {
            assertEquals(LinkedHashSet::class, numSet::class)
            numSet as MutableSet  // still need the smart cast in order for add to compile
            numSet.add(42)
            assertTrue(numSet.contains(42))  // but no exception this time
            assertDoesNotThrow { numSet.add(42) }
        }
    }

    @Nested
    inner class MapTests {
        @Test
        internal fun `mapOf creates a map`() {
            assertThat(numMap.keys, containsInAnyOrder(1, 3, 4))
            assertThat(numMap.values, containsInAnyOrder("one", "three", "four"))
            assertThat(3, `is`(numMap.size))
        }
    }
}