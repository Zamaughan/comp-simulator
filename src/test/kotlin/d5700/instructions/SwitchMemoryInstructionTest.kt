package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SwitchMemoryInstructionTest {

    @Test
    fun `toggles M from false to true`() {
        val cpu = CPU()
        assertEquals(false, cpu.registers.m)
        SwitchMemoryInstruction().execute(cpu)
        assertEquals(true, cpu.registers.m)
    }

    @Test
    fun `toggles M back from true to false`() {
        val cpu = CPU()
        cpu.registers.m = true
        SwitchMemoryInstruction().execute(cpu)
        assertEquals(false, cpu.registers.m)
    }
}
