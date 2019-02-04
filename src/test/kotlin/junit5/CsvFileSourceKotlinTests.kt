package junit5

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class CsvFileSourceKotlinTests {
    @ParameterizedTest
    @CsvFileSource(resources = ["/two-column.csv"], numLinesToSkip = 1)
    fun `test with csv file source`(first: String, second: Int) {
        assertNotNull(first)
        assertNotEquals(0, second)
    }
}