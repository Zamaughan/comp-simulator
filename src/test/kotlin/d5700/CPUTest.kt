package d5700

import d5700.memory.ROM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CPUTest {

    @Test
    fun `step halts on the 0000 instruction`() {
        val cpu = CPU()
        cpu.rom.load(byteArrayOf(0x00, 0x00))
        assertFalse(cpu.step())
    }

    @Test
    fun `step executes an instruction and advances P`() {
        val cpu = CPU()
        cpu.rom.load(byteArrayOf(0x00.toByte(), 0xFF.toByte(), 0x00, 0x00))
        val stillRunning = cpu.step()
        assertTrue(stillRunning)
        assertEquals(0xFF, cpu.registers.getGeneral(0))
        assertEquals(2, cpu.registers.p)
    }

    @Test
    fun `step halts when P runs past the end of ROM`() {
        val cpu = CPU(rom = ROM(sizeInBytes = 4))
        cpu.registers.p = 4
        assertFalse(cpu.step())
    }

    @Test
    fun `activeMemory is RAM when M is false`() {
        val cpu = CPU()
        cpu.registers.m = false
        assertSame(cpu.ram, cpu.activeMemory)
    }

    @Test
    fun `activeMemory is ROM when M is true`() {
        val cpu = CPU()
        cpu.registers.m = true
        assertSame(cpu.rom, cpu.activeMemory)
    }

    @Test
    fun `a full program runs multiple steps until it halts`() {
        // STORE r0=0x05, STORE r1=0x03, ADD r0+r1->r2, halt
        val cpu = CPU()
        cpu.rom.load(
            byteArrayOf(
                0x00, 0x05,
                0x01.toByte(), 0x03,
                0x10, 0x12,
                0x00, 0x00
            )
        )
        var steps = 0
        while (cpu.step()) {
            steps++
            if (steps > 10) break // safety net against an infinite loop in a broken test
        }
        assertEquals(3, steps)
        assertEquals(0x08, cpu.registers.getGeneral(2))
    }
}
