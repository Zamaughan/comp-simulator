package d5700.instructions

import d5700.CPU
import d5700.D5700Exception
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class JumpInstructionTest {

    @Test
    fun `sets P to the target address`() {
        val cpu = CPU()
        JumpInstruction(address = 0x100).execute(cpu)
        assertEquals(0x100, cpu.registers.p)
    }

    @Test
    fun `does not add the usual +2 on top of the jump target`() {
        val cpu = CPU()
        cpu.registers.p = 50
        JumpInstruction(address = 0x100).execute(cpu)
        assertEquals(0x100, cpu.registers.p)
    }

    @Test
    fun `throws when the target address is not divisible by 2`() {
        val cpu = CPU()
        assertThrows(D5700Exception::class.java) { JumpInstruction(address = 0x101).execute(cpu) }
    }
}
