package d5700.instructions

import d5700.CPU
import d5700.D5700Exception
import d5700.Screen
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DrawInstructionTest {

    @BeforeEach
    fun clearScreen() {
        Screen.instance.reset()
    }

    @Test
    fun `draws the character in rX at the literal row and column`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 'H'.toInt())
        DrawInstruction(registerX = 0, row = 0, column = 1).execute(cpu)
        assertEquals('H', Screen.instance.charAt(0, 1))
    }

    @Test
    fun `row and column are literal nibble values, not register lookups`() {
        // r0 holds a large value; if row/column were register lookups this
        // would be an out-of-bounds row/column instead of (0, 0).
        val cpu = CPU()
        cpu.registers.setGeneral(0, 'H'.toInt())
        DrawInstruction(registerX = 0, row = 0, column = 0).execute(cpu)
        assertEquals('H', Screen.instance.charAt(0, 0))
    }

    @Test
    fun `throws when the byte to draw is greater than 0x7F`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0x80)
        assertThrows(D5700Exception::class.java) {
            DrawInstruction(registerX = 0, row = 0, column = 0).execute(cpu)
        }
    }
}
