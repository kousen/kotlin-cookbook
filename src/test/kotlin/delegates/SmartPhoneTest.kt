package delegates

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SmartPhoneTest() {
    private val smartPhone: SmartPhone = SmartPhone()

    @Test
    fun `Dialing delegates to internal phone`() {
        assertEquals("Dialing 555-1234...", smartPhone.dial("555-1234"))
    }

    @Test
    fun `Taking picture delegates to internal camera`() {
        assertEquals("Taking picture...", smartPhone.takePicture())
    }
}