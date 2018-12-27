package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.UnsupportedOperationException
import java.util.*
import kotlin.collections.LinkedHashSet

class CollectionsTests {
    private lateinit var numList : List<Int>
    private lateinit var mutableNums : MutableList<Int>
    private lateinit var numSet: Set<Int>
    private lateinit var numMap: Map<Int, String>

    @BeforeEach
    fun init() {
        numList = listOf(3, 1, 4, 1, 5, 9)
        mutableNums = mutableListOf(3, 1, 4, 1, 5, 9)
        numSet = setOf(3, 1, 4, 1, 5, 9)
        numMap = mapOf(3 to "three", 1 to "one", 4 to "four")
    }

    @Nested
    inner class ListTests {
        @Test
        fun `plus and minus operators add and remove elements`() {
            val listPlusElement = numList + 2
            assertTrue(2 in listPlusElement)

            val listPlusList = numList + listOf(2, 6, 5)
            assertThat(listPlusList, contains(3, 1, 4, 1, 5, 9, 2, 6, 5))

            val listMinusElement = numList - 1
            assertThat(listMinusElement, contains(3, 4, 1, 5, 9))

            val listMinusList = numList - listOf(3, 1, 4, 9)
            assertThat(listMinusList, contains(5))  // removes both 1's (??)
        }

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
        internal fun `instantiating a linked list`() {
            val list = LinkedList<Int>()
            list.add(3)
            list.add(1)
            list.addLast(999)
            list[2] = 4
            list.addAll(listOf(1, 5, 9, 2, 6, 5))
            assertThat(list, contains(3, 1, 4, 1, 5, 9, 2, 6, 5))
        }

        @Test
        internal fun covariance() {
            val circles = listOf(Circle(radius = 1.0), Circle(radius = 2.0))
            val squares = listOf(Square(side = 1.0), Square(side = 2.0))
            val shapes = mutableListOf<Shape>()
            shapes.addAll(circles)
            shapes.addAll(squares)
            val totalArea = shapes.map { it.getArea() }.sum()
            println(totalArea)
        }
    }

    @Nested
    inner class SetTests {
        @Test
        internal fun `setOf eliminates duplicates`() {
            assertThat(numSet.size, `is`(5))
            assertThat(numSet, containsInAnyOrder(1, 3, 4, 5, 9))
        }


        @Test
        internal fun `setOf creates mutable set`() {
            val set = setOf(1, 2, 3, 3)
            assertEquals(LinkedHashSet::class, set::class)
            set as MutableSet  // still need the smart cast in order for add to compile
            set.add(42)
            assertTrue(set.contains(42))  // but no exception this time
            assertDoesNotThrow { set.add(42) }
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

        @Test
        internal fun mutableMap() {
            val mutableMap = mutableMapOf("a" to 1, "b" to 2, "c" to 2)
            assertThat(mutableMap.keys, contains("a", "b", "c"))
            assertThat(mutableMap.values, contains(1, 2, 2))

            mutableMap.put("d", 1)
            assertThat(mutableMap.size, `is`(4))
        }

    }
}