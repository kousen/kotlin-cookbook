package functional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class FilteringTests {
    @Test
    internal fun `filtering instances`() {
        val list = listOf("a", LocalDate.now(), 3, 1, 4, "b")

        val all = list.filterIsInstance<Any>()
        val strings = list.filterIsInstance<String>()
        // type is reified, so inferred type is List<String>

        val ints = list.filterIsInstance<Int>()
        val dates = list.filterIsInstance<LocalDate>()

        assertThat(all).isEqualTo(list)
        assertThat(strings).contains("a", "b")
        assertThat(ints).contains(1, 3, 4)
        assertThat(dates).containsExactly(LocalDate.now())

        for (s in strings) {
            s.length
        }
    }

    @Test
    internal fun `regular filter with a predicate`() {
        val list = listOf("a", LocalDate.now(), 3, 1, 4, "b")

        val strings = list.filterIsInstance<String>()

        // type is erased, so inferred type is List<Any>
        assertTrue(strings.all { it is String })
    }

    @Test
    internal fun `filtering to a typed collection`() {
        val list = listOf("a", LocalDate.now(), 3, 1, 4, "b")

        val all = list.filterIsInstanceTo(mutableListOf<Any>())
        val strings = list.filterIsInstanceTo(mutableListOf<String>())
        val ints = list.filterIsInstanceTo(mutableListOf<Int>())
        val dates = list.filterIsInstanceTo(mutableListOf<LocalDate>())

        assertThat(all).isEqualTo(list)
        assertThat(strings).contains("a", "b")
        assertThat(ints).contains(1, 3, 4)
        assertThat(dates).containsExactly(LocalDate.now())
    }
}