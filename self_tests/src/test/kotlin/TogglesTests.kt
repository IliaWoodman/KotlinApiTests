import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import toggles.Toggles.LOGS
import toggles.Toggles.TEST_TOGGLE

class TogglesTests {

    @Test
    fun getDefaultBooleanValueTest() {
        assertTrue(LOGS.isActive())
    }

    @Test
    fun setDefaultBooleanValueTest() {
        val toggle = LOGS
        toggle.setValue(false)
        assertFalse(toggle.isActive())
    }

    @Test
    fun getGradleBooleanValueTest() {
        val toggle = LOGS
        System.setProperty(toggle.name, "false")
        assertFalse(toggle.isActive())
    }

    @Test
    fun getGradleInvalidBooleanValueTest() {
        val toggle = LOGS
        System.setProperty(toggle.name, "invalidValue")
        val exception = assertThrows(RuntimeException::class.java) {
            toggle.isActive()
        }
        assertEquals("-DLOGS == \"invalidValue\". Допустимые значения: true или false", exception.message)
    }

    @Test
    fun getDefaultValueTest() {
        assertEquals("TestToggle", TEST_TOGGLE.getValue())
    }

    @Test
    fun setDefaultValueTest() {
        TEST_TOGGLE.setValue("CustomValue")
        assertEquals("CustomValue", TEST_TOGGLE.getValue())
    }
}