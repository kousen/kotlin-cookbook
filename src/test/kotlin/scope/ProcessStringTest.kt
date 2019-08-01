package scope

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ProcessStringTest {

    @Test
    fun processEmptyNullableString() {
        assertEquals("Empty", processNullableString(""))
    }

    @Test
    fun processBlankNullableString() {
        assertEquals("Blank", processNullableString("\t\n "))
    }

    @Test
    fun processNullString() {
        assertEquals("Null", processNullableString(null))
    }

    @Test
    fun processRegularNullableString() {
        assertEquals("Abc", processNullableString("abc"))
    }

    @Test
    fun processEmptyString() {
        assertEquals("Empty", processString(""))
    }

    @Test
    fun processBlankString() {
        assertEquals("Blank", processString("\t\n "))
    }

    @Test
    fun processRegularString() {
        assertEquals("Abc", processString("abc"))
    }
}