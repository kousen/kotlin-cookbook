package misc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PalindromeTest {
    @Test
    internal fun `these are palindromes (for extension function)`() {
        assertAll(
            { assertTrue("Madam, in Eden, I'm Adam".isPalindrome()) },
            { assertTrue("Flee to me, remote elf!".isPalindrome()) },
            { assertTrue("A Santa pets rats, as Pat taps a star step at NASA".isPalindrome()) }
        )
    }

    @Test
    internal fun `not a palindrome (for extension function)`() {
        assertFalse("this is not a palindrome". isPalindrome())
    }


    @Test
    internal fun `these are palindromes (for function with arg)`() {
        assertAll(
            { assertTrue(isPal("Madam, in Eden, I'm Adam")) },
            { assertTrue(isPal("Flee to me, remote elf!")) },
            { assertTrue(isPal("A Santa pets rats, as Pat taps a star step at NASA")) }
        )
    }

    @Test
    internal fun `not a palindrome (for function with arg)`() {
        assertFalse(isPal("this is not a palindrome"))
    }

    // Taken from https://speakerdeck.com/svtk/4-kotlin-types-kotlin-workshop?slide=19
    @Test
    internal fun `demonstrate replace with a string vs regex`() {
        assertAll(
            { assertEquals("one*two*", "one.two.".replace(".", "*")) },
            { assertEquals("********", "one.two.".replace(".".toRegex(), "*")) }
        )
    }
}