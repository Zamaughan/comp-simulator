package d5700

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class RegistersTest {

    @Test
    fun `general purpose registers default to zero`() {
        val registers = Registers()
        for (i in 0..7) {
            assertEquals(0, registers.getGeneral(i))
        }
    }

    @Test
    fun `general purpose registers store and retrieve values`() {
        val registers = Registers()
        registers.setGeneral(3, 0x42)
        assertEquals(0x42, registers.getGeneral(3))
    }

    @Test
    fun `general purpose registers wrap values larger than one byte`() {
        val registers = Registers()
        registers.setGeneral(0, 0x1FF) // 511
        assertEquals(0xFF, registers.getGeneral(0))
    }

    @Test
    fun `general purpose registers wrap negative values as two's complement`() {
        val registers = Registers()
        registers.setGeneral(0, -1)
        assertEquals(0xFF, registers.getGeneral(0))
    }

    @Test
    fun `invalid register index throws on read`() {
        val registers = Registers()
        assertThrows(IllegalArgumentException::class.java) { registers.getGeneral(8) }
    }

    @Test
    fun `invalid register index throws on write`() {
        val registers = Registers()
        assertThrows(IllegalArgumentException::class.java) { registers.setGeneral(-1, 1) }
    }

    @Test
    fun `T register masks to a single byte`() {
        val registers = Registers()
        registers.t = 0x1FF
        assertEquals(0xFF, registers.t)
    }

    @Test
    fun `A register masks to 16 bits`() {
        val registers = Registers()
        registers.a = 0x1FFFF
        assertEquals(0xFFFF, registers.a)
    }

    @Test
    fun `M register defaults to false (RAM)`() {
        val registers = Registers()
        assertEquals(false, registers.m)
    }
}
