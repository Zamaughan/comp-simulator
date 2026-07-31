package d5700

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScreenTest {

    @BeforeEach
    fun clearScreen() {
        Screen.instance.reset()
    }

    @Test
    fun `instance always returns the same object (singleton)`() {
        assertSame(Screen.instance, Screen.instance)
    }

    @Test
    fun `cells default to blank`() {
        assertEquals(' ', Screen.instance.charAt(0, 0))
    }

    @Test
    fun `draw writes the ASCII character at the given cell`() {
        Screen.instance.draw(row = 2, column = 3, character = 'H'.toInt())
        assertEquals('H', Screen.instance.charAt(2, 3))
    }

    @Test
    fun `draw rejects a row outside the 8x8 grid`() {
        assertThrows(IllegalArgumentException::class.java) {
            Screen.instance.draw(row = 8, column = 0, character = 'A'.toInt())
        }
    }

    @Test
    fun `draw rejects a column outside the 8x8 grid`() {
        assertThrows(IllegalArgumentException::class.java) {
            Screen.instance.draw(row = 0, column = -1, character = 'A'.toInt())
        }
    }

    @Test
    fun `draw rejects a byte greater than 0x7F`() {
        assertThrows(D5700Exception::class.java) {
            Screen.instance.draw(row = 0, column = 0, character = 0x80)
        }
    }

    @Test
    fun `render produces one line per row`() {
        val rendered = Screen.instance.render()
        assertEquals(8, rendered.split("\n").size)
    }

    @Test
    fun `reset clears every cell back to blank`() {
        Screen.instance.draw(row = 0, column = 0, character = 'X'.toInt())
        Screen.instance.reset()
        assertEquals(' ', Screen.instance.charAt(0, 0))
    }
}
