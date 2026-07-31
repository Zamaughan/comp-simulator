package d5700.memory

import d5700.D5700Exception
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class RAMTest {

    @Test
    fun `is 4kb by default`() {
        assertEquals(4096, RAM().sizeInBytes)
    }

    @Test
    fun `reads default to zero`() {
        assertEquals(0, RAM().read(0))
    }

    @Test
    fun `write then read returns the same value`() {
        val ram = RAM()
        ram.write(10, 0xAB)
        assertEquals(0xAB, ram.read(10))
    }

    @Test
    fun `write masks values to a single byte`() {
        val ram = RAM()
        ram.write(0, 0x1FF)
        assertEquals(0xFF, ram.read(0))
    }

    @Test
    fun `reading out of bounds throws`() {
        val ram = RAM(sizeInBytes = 16)
        assertThrows(D5700Exception::class.java) { ram.read(16) }
    }

    @Test
    fun `writing out of bounds throws`() {
        val ram = RAM(sizeInBytes = 16)
        assertThrows(D5700Exception::class.java) { ram.write(-1, 5) }
    }
}
