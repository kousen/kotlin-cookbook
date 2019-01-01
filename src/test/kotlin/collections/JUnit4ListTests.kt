package collections

import org.junit.*
import org.junit.Assert.assertEquals
import java.util.*

class JUnit4ListTests {
    companion object {
        @JvmStatic
        private val strings = listOf("this", "is", "a", "list", "of", "strings")

        @BeforeClass @JvmStatic
        fun runBefore() {
            println("BeforeClass: $strings")
        }

        @AfterClass @JvmStatic
        fun runAfter() {
            println("AfterClass: $strings")
        }
    }

    private val modifiable = ArrayList<Int>()

    @Before
    fun setUp() {
        println("Before: $modifiable")
        modifiable.add(3)
        modifiable.add(1)
        modifiable.add(4)
        modifiable.add(1)
        modifiable.add(5)
    }

    @After
    fun finish() {
        println("After: $modifiable")
    }

    @Test
    fun addElementsToList() {
        modifiable.add(9)
        modifiable.add(2)
        modifiable.add(6)
        modifiable.add(5)
        assertEquals(9, modifiable.size)
    }

    @Test
    fun size() {
        println("Testing size")
        assertEquals(6, strings.size)
        assertEquals(5, modifiable.size)
    }

    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun accessBeyondEndThrowsException() {
        println("Testing out of bounds exception")
        strings[99]
        assertEquals(6, strings.size)
    }
}