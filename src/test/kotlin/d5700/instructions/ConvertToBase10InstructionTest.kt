package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConvertToBase10InstructionTest {

    @Test
    fun `splits a byte into hundreds, tens, and ones digits at A, A+1, A+2`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 123)
        cpu.registers.a = 10
        ConvertToBase10Instruction(registerX = 0).execute(cpu)
        assertEquals(1, cpu.ram.read(10))
        assertEquals(2, cpu.ram.read(11))
        assertEquals(3, cpu.ram.read(12))
    }

    @Test
    fun `handles single-digit values with leading zero digits`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 7)
        cpu.registers.a = 0
        ConvertToBase10Instruction(registerX = 0).execute(cpu)
        assertEquals(0, cpu.ram.read(0))
        assertEquals(0, cpu.ram.read(1))
        assertEquals(7, cpu.ram.read(2))
    }

    @Test
    fun `handles the maximum byte value 255`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 255)
        cpu.registers.a = 0
        ConvertToBase10Instruction(registerX = 0).execute(cpu)
        assertEquals(2, cpu.ram.read(0))
        assertEquals(5, cpu.ram.read(1))
        assertEquals(5, cpu.ram.read(2))
    }
}
