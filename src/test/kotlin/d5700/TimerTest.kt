package d5700

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TimerTest {

    @Test
    fun `decrements T towards 0 over real time`() {
        val registers = Registers()
        registers.t = 3
        val timer = Timer(registers)
        timer.start()
        // 3 ticks at 16ms each is ~48ms; give it generous headroom.
        Thread.sleep(300)
        timer.stop()
        assertEquals(0, registers.t)
    }

    @Test
    fun `does not decrement below 0`() {
        val registers = Registers()
        registers.t = 0
        val timer = Timer(registers)
        timer.start()
        Thread.sleep(100)
        timer.stop()
        assertEquals(0, registers.t)
    }

    @Test
    fun `stop halts further decrementing`() {
        val registers = Registers()
        registers.t = 200
        val timer = Timer(registers)
        timer.start()
        Thread.sleep(50)
        timer.stop()
        val valueAfterStop = registers.t
        Thread.sleep(100)
        assertEquals(valueAfterStop, registers.t)
        assertTrue(valueAfterStop < 200)
    }
}
