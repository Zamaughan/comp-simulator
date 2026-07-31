package d5700.instructions

import d5700.CPU
import d5700.D5700Exception
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ConvertByteToAsciiInstructionTest {

    @Test
    fun `converts a decimal digit to its ASCII code`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        ConvertByteToAsciiInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals('5'.toInt(), cpu.registers.getGeneral(1))
    }

    @Test
    fun `converts a hex letter digit (A-F) to its ASCII code`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0xE)
        ConvertByteToAsciiInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals('E'.toInt(), cpu.registers.getGeneral(1))
    }

    @Test
    fun `can write the result back into the same register`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0)
        ConvertByteToAsciiInstruction(registerX = 0, registerY = 0).execute(cpu)
        assertEquals('0'.toInt(), cpu.registers.getGeneral(0))
    }

    @Test
    fun `throws when the source register holds more than a single hex digit`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0x10)
        assertThrows(D5700Exception::class.java) {
            ConvertByteToAsciiInstruction(registerX = 0, registerY = 1).execute(cpu)
        }
    }
}
