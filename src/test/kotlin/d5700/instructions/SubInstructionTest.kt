package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SubInstructionTest {

    @Test
    fun `subtracts rY from rX into rZ`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 10)
        cpu.registers.setGeneral(1, 3)
        SubInstruction(registerX = 0, registerY = 1, registerZ = 2).execute(cpu)
        assertEquals(7, cpu.registers.getGeneral(2))
    }

    @Test
    fun `wraps around on underflow below 0`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0)
        cpu.registers.setGeneral(1, 1)
        SubInstruction(registerX = 0, registerY = 1, registerZ = 2).execute(cpu)
        assertEquals(0xFF, cpu.registers.getGeneral(2))
    }
}
