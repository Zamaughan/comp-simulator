package d5700.instructions

import d5700.CPU
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InstructionFactoryTest {

    @Test
    fun `decodes STORE (0)`() {
        assertTrue(InstructionFactory.decode(0x00, 0xFF) is StoreInstruction)
        val cpu = CPU()
        InstructionFactory.decode(0x00, 0xFF).execute(cpu)
        assertEquals(0xFF, cpu.registers.getGeneral(0))
    }

    @Test
    fun `decodes ADD (1) per the spec's 1010 example`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 3)
        cpu.registers.setGeneral(1, 4)
        InstructionFactory.decode(0x10, 0x10).execute(cpu)
        assertEquals(7, cpu.registers.getGeneral(0))
    }

    @Test
    fun `decodes SUB (2) per the spec's 2010 example`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 10)
        cpu.registers.setGeneral(1, 4)
        InstructionFactory.decode(0x20, 0x10).execute(cpu)
        assertEquals(6, cpu.registers.getGeneral(0))
    }

    @Test
    fun `decodes READ (3) per the spec's 3700 example`() {
        assertTrue(InstructionFactory.decode(0x37, 0x00) is ReadInstruction)
    }

    @Test
    fun `decodes WRITE (4) per the spec's 4300 example`() {
        assertTrue(InstructionFactory.decode(0x43, 0x00) is WriteInstruction)
    }

    @Test
    fun `decodes JUMP (5) per the spec's 51F2 example`() {
        val instruction = InstructionFactory.decode(0x51, 0xF2)
        val cpu = CPU()
        instruction.execute(cpu)
        assertEquals(0x1F2, cpu.registers.p)
    }

    @Test
    fun `decodes READ_KEYBOARD (6)`() {
        assertTrue(InstructionFactory.decode(0x62, 0x00) is ReadKeyboardInstruction)
    }

    @Test
    fun `decodes SWITCH_MEMORY (7000)`() {
        assertTrue(InstructionFactory.decode(0x70, 0x00) is SwitchMemoryInstruction)
    }

    @Test
    fun `decodes SKIP_EQUAL (8) per the spec's 8120 example`() {
        assertTrue(InstructionFactory.decode(0x81, 0x20) is SkipEqualInstruction)
    }

    @Test
    fun `decodes SKIP_NOT_EQUAL (9) per the spec's 9120 example`() {
        assertTrue(InstructionFactory.decode(0x91, 0x20) is SkipNotEqualInstruction)
    }

    @Test
    fun `decodes SET_A (A) per the spec's A255 example`() {
        val cpu = CPU()
        InstructionFactory.decode(0xA2, 0x55).execute(cpu)
        assertEquals(0x255, cpu.registers.a)
    }

    @Test
    fun `decodes SET_T (B) per the spec's B0A0 example`() {
        val cpu = CPU()
        InstructionFactory.decode(0xB0, 0xA0).execute(cpu)
        assertEquals(0x0A, cpu.registers.t)
    }

    @Test
    fun `decodes READ_T (C) per the spec's C000 example`() {
        assertTrue(InstructionFactory.decode(0xC0, 0x00) is ReadTInstruction)
    }

    @Test
    fun `decodes CONVERT_TO_BASE_10 (D) per the spec's D200 example`() {
        assertTrue(InstructionFactory.decode(0xD2, 0x00) is ConvertToBase10Instruction)
    }

    @Test
    fun `decodes CONVERT_BYTE_TO_ASCII (E) per the spec's E010 example`() {
        val cpu = CPU()
        cpu.registers.setGeneral(0, 0)
        InstructionFactory.decode(0xE0, 0x10).execute(cpu)
        assertEquals('0'.toInt(), cpu.registers.getGeneral(1))
    }

    @Test
    fun `decodes DRAW (F) per the spec's F123 example`() {
        assertTrue(InstructionFactory.decode(0xF1, 0x23) is DrawInstruction)
    }
}
