package collections

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MapTests {
    @Test
    internal fun `create map using infix to function`() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 2)
        // Destructuring on Map.Entry. See component1 and component2 extension functions
        //   in https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/
        for ((key,value) in map) {
            println("$key maps to $value")
        }

        assertAll(
            { assertThat(map, hasKey("a")) },
            { assertThat(map, hasKey("b")) },
            { assertThat(map, hasKey("c")) },
            { assertThat(map, hasValue(1)) },
            { assertThat(map, hasValue(2)) })
    }

    @Test
    internal fun `create a Pair from constructor vs to function`() {
        val p1 = Pair("a", 1)
        val p2 = "a" to 1

        assertAll(
            { assertThat(p1.first, `is`("a")) },
            { assertThat(p1.second, `is`(1)) },
            { assertThat(p2.first, `is`("a")) },
            { assertThat(p2.second, `is`(1)) },
            { assertThat(p1, `is`(equalTo(p2)))}
        )
    }

    @Test
    internal fun `destructuring a Pair`() {
        val pair = "a" to 1

        val (x,y) = pair

        assertThat(x, `is`("a"))
        assertThat(y, `is`(1))
    }
}