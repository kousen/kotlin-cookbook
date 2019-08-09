package misc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PalindromeTest {
    @Test
    internal fun `these are palindromes`() {
        assertAll(
            { assertTrue("Madam, in Eden, I'm Adam".isPalindrome()) },
            { assertTrue("Flee to me, remote elf!".isPalindrome()) },
            { assertTrue("A Santa pets rats as Pat taps a star step at NASA".isPalindrome()) }
        )
    }

    @Test
    internal fun `not a palindrome`() {
        assertFalse("this is not a palindrome". isPalindrome())
    }
}