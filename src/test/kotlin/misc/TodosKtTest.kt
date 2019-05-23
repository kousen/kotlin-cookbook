package misc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TodosKtTest {
    @Test
    internal fun `todo test`() {
        val exception = assertThrows<NotImplementedError> {
            TODO("seriously, finish this")
        }
        assertEquals("An operation is not implemented: seriously, finish this",
            exception.message)
    }
}