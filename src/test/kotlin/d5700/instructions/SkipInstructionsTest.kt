package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SkipEqualInstructionTest {

    @Test
    fun `advances by 4 (skipping the next instruction) when the registers are equal`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 5)
        SkipEqualInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals(4, cpu.registers.p)
    }

    @Test
    fun `advances by only 2 when the registers differ`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 9)
        SkipEqualInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals(2, cpu.registers.p)
    }
}

class SkipNotEqualInstructionTest {

    @Test
    fun `advances by 4 (skipping the next instruction) when the registers differ`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 9)
        SkipNotEqualInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals(4, cpu.registers.p)
    }

    @Test
    fun `advances by only 2 when the registers are equal`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 5)
        cpu.registers.setGeneral(1, 5)
        SkipNotEqualInstruction(registerX = 0, registerY = 1).execute(cpu)
        assertEquals(2, cpu.registers.p)
    }
}
