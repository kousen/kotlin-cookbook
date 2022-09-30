package simple

import operators.pow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.awt.Color
import kotlin.math.absoluteValue

class BitwiseTests {
    @Test
    internal fun referenceGuideTests() {
        // Note: available for Int and Long only
        assertThat(4).isEqualTo(1 shl 2)  // Java <<
        assertThat(4).isEqualTo(16 shr 2) // Java >>
        assertThat(4).isEqualTo(16 ushr 2)  // Java >>>
        assertThat(0).isEqualTo(2 and 4)  // Java &
        assertThat(3).isEqualTo(2 or 3)  // Java |
        assertThat(1).isEqualTo(4 xor 5)  // Java ^
        assertThat(-5).isEqualTo(4.inv())   // Java ~
        assertThat(4).isEqualTo(0b00000100)
    }

    @Test
    internal fun `doubling and halving`() {
        assertAll("left shifts doubling from 1",    // 0000_0001
            { assertThat(2).isEqualTo(1 shl 1) },  // 0000_0010
            { assertThat(4).isEqualTo(1 shl 2) },  // 0000_0100
            { assertThat(8).isEqualTo(1 shl 3) },  // 0000_1000
            { assertThat(16).isEqualTo(1 shl 4) },  // 0001_0000
            { assertThat(32).isEqualTo(1 shl 5) },  // 0010_0000
            { assertThat(64).isEqualTo(1 shl 6) },  // 0100_0000
            { assertThat(128).isEqualTo(1 shl 7) }   // 1000_0000
        )

        assertAll("right shifts halving from 235",   // 1110_1011
            { assertThat(117).isEqualTo(235 shr 1) }, // 0111_0101
            { assertThat(58).isEqualTo(235 shr 2) }, // 0011_1010
            { assertThat(29).isEqualTo(235 shr 3) }, // 0001_1101
            { assertThat(14).isEqualTo(235 shr 4) }, // 0000_1110
            { assertThat(7).isEqualTo(235 shr 5) }, // 0000_0111
            { assertThat(3).isEqualTo(235 shr 6) }, // 0000_0011
            { assertThat(1).isEqualTo(235 shr 7) }  // 0000_0001
        )
    }

    @Suppress("INTEGER_OVERFLOW")
    @Test
    internal fun `unsigned right shifts`() {
        val n1 = 5
        val n2 = -5
        println(n1.toString(2))  //  0b0101
        println(n2.toString(2))  // -0b0101

        assertThat(n1 shr 1).isEqualTo(0b0010)      // 2
        assertThat(n1 ushr 1).isEqualTo(0b0010)      // 2

        assertThat(n2 shr 1).isEqualTo(-0b0011)     // -3
        assertThat(n2 ushr 1).isEqualTo(0x7fff_fffd) // 2_147_483_645

        val x = 2147483645
        println(x.toString(16))
        println(Integer.MAX_VALUE - x + 1)
        println(twosComplement(x, 32))


        val high = (0.99 * Int.MAX_VALUE).toInt()
        val low = (0.75 * Int.MAX_VALUE).toInt()
        val mid1 = (high + low) / 2      // sum gives overflow
        val mid2 = (high + low) ushr 1
        assertTrue(mid1 !in low..high)
        assertTrue(mid2 in low..high)
    }

    @Test
    internal fun test_twos_complement() {
        assertThat(11).isEqualTo(twosComplement(-5, 4))
        assertThat(251).isEqualTo(twosComplement(-5, 8))
        assertThat(220).isEqualTo(twosComplement(-36, 8))
        assertThat(3).isEqualTo(twosComplement(2147483645, 32))
    }

    fun twosComplement(i: Int, base: Int) =
        when {
            i == 0 -> 0
            base == 32 -> Integer.MAX_VALUE - i.absoluteValue + 1
            else -> 2.pow(base) - i.absoluteValue
        }

    @Test
    internal fun `odds and evens`() {
        (1..4).forEach { n ->
            if (n % 2 == 0) assertTrue(n and 0x1 == 0)
            else assertFalse(n and 0x1 == 0)
        }
    }

    private fun intsFromColor(color: Color): List<Int> {
        val rgb = color.rgb
        val alpha = rgb shr 24 and 0xff
        val red = rgb shr 16 and 0xff
        val green = rgb shr 8 and 0xff
        val blue = rgb and 0xff
        return listOf(alpha, red, green, blue)
    }

    private fun colorFromInts(alpha: Int, red: Int, green: Int, blue: Int) =
        (alpha and 0xff shl 24) or
                (red and 0xff shl 16) or
                (green and 0xff shl 8) or
                (blue and 0xff)

    @Test
    internal fun `colors as ints`() {
        val color = Color.MAGENTA
        val (a, r, g, b) = intsFromColor(color)

        assertThat(color.alpha).isEqualTo(a)
        assertThat(color.red).isEqualTo(r)
        assertThat(color.green).isEqualTo(g)
        assertThat(color.blue).isEqualTo(b)
        println("$a, ($r, $g, $b), ${color.rgb}")

        val intColor = colorFromInts(a, r, g, b)
        val color1 = Color(intColor, true)
        assertThat(color1).isEqualTo(color)
    }

    @Suppress("LocalVariableName")
    @Test
    internal fun `and, or, xor`() {
        val n1 = 0b0000_1100      // 12
        val n2 = 0b0001_1001      // 25

        val n1_and_n2 = n1 and n2
        val n1_or_n2 = n1 or n2
        val n1_xor_n2 = n1 xor n2

        assertThat(n1_and_n2).isEqualTo(0b0000_1000) //  8
        assertThat(n1_or_n2).isEqualTo(0b0001_1101) // 29
        assertThat(n1_xor_n2).isEqualTo(0b0001_0101) // 21
    }

    @Test
    internal fun `printing to different bases`() {
        (Character.MIN_RADIX..Character.MAX_RADIX).forEach { radix ->
            println("${radix.toString().padStart(2)}: ${42.toString(radix)}")
        }
    }

    @Test
    internal fun `binary joke`() {
        val binaryJoke = """
            There are ${2.toString(2)} kinds of people
            Those who know binary, and those who don't
        """.trimIndent()

        assertTrue(binaryJoke.contains("10".toRegex()))
        println(binaryJoke)

        val ternaryJoke = """
            Actually, there are ${3.toString(3)} kinds of people
            Those who know binary, those who don't,
            And those who didn't realize this was actually a ternary joke
        """.trimIndent()
        println(ternaryJoke)
        assertTrue(ternaryJoke.contains("10".toRegex()))
    }
}