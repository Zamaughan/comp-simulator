package d5700

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Runs the actual .out ROM files USU provided against the CPU directly
 * (stepping it in a tight loop rather than going through Computer's
 * real-time 500Hz scheduler) so these tests are fast and deterministic.
 * This is the same fetch-decode-execute logic Computer drives in
 * production; only the timing source differs.
 */
class ProgramIntegrationTest {

    @BeforeEach
    fun clearScreen() {
        Screen.instance.reset()
    }

    private fun runToHalt(cpu: CPU, maxSteps: Int = 10_000) {
        var steps = 0
        while (cpu.step()) {
            steps++
            if (steps > maxSteps) {
                throw IllegalStateException("Program did not halt within $maxSteps steps")
            }
        }
    }

    @Test
    fun `hello rom draws HELLO on the top row`() {
        val cpu = CPU()
        cpu.rom.load(File("roms/hello.out").readBytes())
        runToHalt(cpu)
        assertEquals("HELLO", Screen.instance.render().lines()[0].trim())
    }

    @Test
    fun `hello_from_rom rom draws HELLO by reading its data straight out of ROM`() {
        val cpu = CPU()
        cpu.rom.load(File("roms/hello_from_rom.out").readBytes())
        runToHalt(cpu)
        assertEquals("HELLO", Screen.instance.render().lines()[0].trim())
    }

    @Test
    fun `addition rom adds two keyboard-entered digits and displays the sum`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("7", "8"))
        cpu.rom.load(File("roms/addition.out").readBytes())
        runToHalt(cpu)
        assertEquals("015", Screen.instance.render().lines()[0].trim())
    }

    @Test
    fun `subtraction rom subtracts two keyboard-entered digits and displays the result`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("C", "4"))
        cpu.rom.load(File("roms/subtraction.out").readBytes())
        runToHalt(cpu)
        assertEquals("008", Screen.instance.render().lines()[0].trim())
    }

    @Test
    fun `keyboard rom echoes a single keyboard-entered digit`() {
        val cpu = CPU(keyboard = FakeKeyboardInput("A"))
        cpu.rom.load(File("roms/keyboard.out").readBytes())
        runToHalt(cpu)
        assertEquals("010", Screen.instance.render().lines()[0].trim())
    }
}
