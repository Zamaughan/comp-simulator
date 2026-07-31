package d5700.memory

import d5700.D5700Exception
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ROMTest {

    @Test
    fun `is 4kb by default`() {
        assertEquals(4096, ROM().sizeInBytes)
    }

    @Test
    fun `load copies bytes starting at address 0`() {
        val rom = ROM()
        rom.load(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(0x01, rom.read(0))
        assertEquals(0x02, rom.read(1))
        assertEquals(0x03, rom.read(2))
    }

    @Test
    fun `load zero-fills the remainder of the chip`() {
        val rom = ROM(sizeInBytes = 8)
        rom.load(byteArrayOf(0x7F, 0x7F))
        for (i in 2 until 8) {
            assertEquals(0, rom.read(i))
        }
    }

    @Test
    fun `loading a program larger than the chip throws`() {
        val rom = ROM(sizeInBytes = 4)
        assertThrows(D5700Exception::class.java) { rom.load(byteArrayOf(1, 2, 3, 4, 5)) }
    }

    @Test
    fun `writing to a non-writable ROM throws and does not change the value`() {
        val rom = ROM(isWritable = false)
        rom.load(byteArrayOf(0x10))
        assertThrows(D5700Exception::class.java) { rom.write(0, 0x99) }
        assertEquals(0x10, rom.read(0))
    }

    @Test
    fun `writing to a writable ROM chip succeeds`() {
        val rom = ROM(isWritable = true)
        rom.write(0, 0x99)
        assertEquals(0x99, rom.read(0))
    }

    @Test
    fun `reading out of bounds throws`() {
        val rom = ROM(sizeInBytes = 4)
        assertThrows(D5700Exception::class.java) { rom.read(4) }
    }
}
