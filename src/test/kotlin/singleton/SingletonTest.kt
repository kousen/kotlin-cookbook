package singleton

import org.junit.jupiter.api.Test

class SingletonTest {
    @Test
    internal fun `access singleton members`() {
        MySingleton.myFunction()
        MySingleton.myProperty
    }
}