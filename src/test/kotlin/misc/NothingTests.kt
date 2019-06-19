@file:Suppress("USELESS_IS_CHECK")

package misc

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NothingTests {
    @Test
    internal fun `nullable val with no type is Nothing?`() {
        val x = null
        assertTrue(x is Nothing?)
    }


}