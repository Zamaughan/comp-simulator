package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StoreInstructionTest {

    @Test
    fun `stores the literal byte in the target register`() {
        val cpu = CPU()
        StoreInstruction(registerX = 2, byteValue = 0xAB).execute(cpu)
        assertEquals(0xAB, cpu.registers.getGeneral(2))
    }

    @Test
    fun `advances the program counter by 2`() {
        val cpu = CPU()
        StoreInstruction(registerX = 0, byteValue = 0x01).execute(cpu)
        assertEquals(2, cpu.registers.p)
    }
}
