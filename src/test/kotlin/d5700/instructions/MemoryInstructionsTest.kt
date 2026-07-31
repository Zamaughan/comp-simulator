package d5700.instructions

import d5700.CPU
import d5700.D5700Exception
import d5700.memory.ROM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ReadInstructionTest {

    @Test
    fun `reads from RAM when M is false`() {
        val cpu = CPU()
        cpu.registers.a = 5
        cpu.ram.write(5, 0x42)
        ReadInstruction(registerX = 0).execute(cpu)
        assertEquals(0x42, cpu.registers.getGeneral(0))
    }

    @Test
    fun `reads from ROM when M is true`() {
        val cpu = CPU(rom = ROM(isWritable = true))
        cpu.registers.m = true
        cpu.registers.a = 5
        cpu.rom.write(5, 0x77)
        ReadInstruction(registerX = 1).execute(cpu)
        assertEquals(0x77, cpu.registers.getGeneral(1))
    }
}

class WriteInstructionTest {

    @Test
    fun `writes a register's value into RAM when M is false`() {
        val cpu = CPU()
        cpu.registers.a = 9
        cpu.registers.setGeneral(3, 0x21)
        WriteInstruction(registerX = 3).execute(cpu)
        assertEquals(0x21, cpu.ram.read(9))
    }

    @Test
    fun `attempts to write to ROM when M is true and fails on a read-only chip`() {
        val cpu = CPU() // default ROM is non-writable
        cpu.registers.m = true
        cpu.registers.a = 0
        cpu.registers.setGeneral(0, 0x21)
        assertThrows(D5700Exception::class.java) { WriteInstruction(registerX = 0).execute(cpu) }
    }

    @Test
    fun `writing to ROM succeeds on a future writable chip`() {
        val cpu = CPU(rom = ROM(isWritable = true))
        cpu.registers.m = true
        cpu.registers.a = 0
        cpu.registers.setGeneral(0, 0x21)
        WriteInstruction(registerX = 0).execute(cpu)
        assertEquals(0x21, cpu.rom.read(0))
    }
}
