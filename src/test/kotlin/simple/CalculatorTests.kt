package simple

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

// Based on kotlin/gradle sample at https://github.com/junit-team/junit5-samples/tree/r5.3.1/junit5-jupiter-starter-gradle-kotlin
class CalculatorTests {
    @Test
    internal fun `1 + 1 == 2`() {
        assertEquals(2, add(1, 1))
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(
        "0,    1,   1",
        "1,    2,   3",
        "49,  51, 100",
        "1,  100, 101"
    )
    internal fun add(first: Int, second: Int, expectedResult: Int) {
        assertEquals(expectedResult, add(first, second)) {
            "$first + $second should equal $expectedResult"
        }
    }

}