package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SetAInstructionTest {
    @Test
    fun `sets the A register to the literal address`() {
        val cpu = CPU()
        SetAInstruction(address = 0x255).execute(cpu)
        assertEquals(0x255, cpu.registers.a)
    }
}

class SetTInstructionTest {
    @Test
    fun `sets the T register to the literal byte`() {
        val cpu = CPU()
        SetTInstruction(byteValue = 0x3C).execute(cpu)
        assertEquals(0x3C, cpu.registers.t)
    }

    @Test
    fun `matches the spec's B0A0 example (sets T to 0x0A)`() {
        val cpu = CPU()
        SetTInstruction(byteValue = 0x0A).execute(cpu)
        assertEquals(0x0A, cpu.registers.t)
    }
}

class ReadTInstructionTest {
    @Test
    fun `reads the current T value into the target register`() {
        val cpu = CPU()
        cpu.registers.t = 0x2A
        ReadTInstruction(registerX = 4).execute(cpu)
        assertEquals(0x2A, cpu.registers.getGeneral(4))
    }
}
