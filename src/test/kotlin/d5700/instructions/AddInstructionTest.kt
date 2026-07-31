package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AddInstructionTest {

    @Test
    fun `adds two registers into a third`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 7)
        AddInstruction(registerX = 0, registerY = 1, registerZ = 2).execute(cpu)
        assertEquals(12, cpu.registers.getGeneral(2))
    }

    @Test
    fun `can write the result back into one of the source registers`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 7)
        AddInstruction(registerX = 0, registerY = 1, registerZ = 0).execute(cpu)
        assertEquals(12, cpu.registers.getGeneral(0))
    }

    @Test
    fun `wraps around on overflow past 255`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0xFF)
        cpu.registers.setGeneral(1, 0x02)
        AddInstruction(registerX = 0, registerY = 1, registerZ = 2).execute(cpu)
        assertEquals(0x01, cpu.registers.getGeneral(2))
    }
}
