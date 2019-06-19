package simple

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.random.nextInt

class RandomTests {
    @Test
    internal fun `nextInt with no args gives any Int`() {
        val value = Random.nextInt()
        assertTrue(value in Int.MIN_VALUE..Int.MAX_VALUE)
    }

    @Test
    internal fun `nextInt with a range gives value between 0 and limit`() {
        val value = Random.nextInt(10)
        assertTrue(value in 0..10)
    }

    @Test
    internal fun `nextInt with min and max gives value between them`() {
        val value = Random.nextInt(5, 10)
        assertTrue(value in 5..10)
    }

    @Test
    internal fun `nextInt with range returns value in range`() {
        val value = Random.nextInt(7..12)
        assertTrue(value in 7..12)
    }

    @Test
    internal fun `Random function produces a seeded generator`() {
        val r1 = Random(12345)
        val nums1 = (1..10).map { r1.nextInt() }

        val r2 = Random(12345)
        val nums2 = (1..10).map { r2.nextInt() }

        // println(nums1)
        assertEquals(nums1, nums2)
    }
}