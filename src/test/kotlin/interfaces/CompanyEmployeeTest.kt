package interfaces

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CompanyEmployeeTest {
    @Test
    internal fun `Employee has a name`() {
        val employee = CompanyEmployee(first = "Fred",
            last = "Flintstone",
            company = "Slate Rock and Quarry")
        assertEquals("Fred Flintstone working for Slate Rock and Quarry",
            employee.getName())
    }
}