package d5700.instructions

import d5700.CPU
import d5700.FakeKeyboardInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReadKeyboardInstructionTest {

    @Test
    fun `parses a single hex digit`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("7"))
        ReadKeyboardInstruction(registerX = 0).execute(cpu)
        assertEquals(0x7, cpu.registers.getGeneral(0))
    }

    @Test
    fun `parses two hex digits as one byte`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("1F"))
        ReadKeyboardInstruction(registerX = 0).execute(cpu)
        assertEquals(0x1F, cpu.registers.getGeneral(0))
    }

    @Test
    fun `ignores digits past the first two`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("1F23"))
        ReadKeyboardInstruction(registerX = 0).execute(cpu)
        assertEquals(0x1F, cpu.registers.getGeneral(0))
    }

    @Test
    fun `empty input stores 0`() {
        val cpu = CPU(keyboard = FakeKeyboardInput(""))
        ReadKeyboardInstruction(registerX = 0).execute(cpu)
        assertEquals(0, cpu.registers.getGeneral(0))
    }

    @Test
    fun `lowercase hex digits are accepted`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("ab"))
        ReadKeyboardInstruction(registerX = 0).execute(cpu)
        assertEquals(0xAB, cpu.registers.getGeneral(0))
    }
}
