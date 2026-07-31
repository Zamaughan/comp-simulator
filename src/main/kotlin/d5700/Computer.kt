package d5700

import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Computer(private val cpu: CPU = CPU(), private val printScreenEachCycle: Boolean = true) {

    private val timer = Timer(cpu.registers)
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: ScheduledFuture<*>? = null

    fun loadProgram(path: String) {
        val bytes = File(path).readBytes()
        cpu.rom.load(bytes)
    }

    fun start() {
        timer.start()
        cpuFuture = executor.scheduleAtFixedRate({
            runCycle()
        }, 0, 1000L / 500L, TimeUnit.MILLISECONDS)
    }

    private fun runCycle() {
        try {
            val stillRunning = cpu.step()
            if (!stillRunning) {
                stop()
            }
        } catch (error: Exception) {
            println("D5700 program terminated with an error: ${error.message}")
            stop()
        }
    }

    fun stop() {
        cpuFuture?.cancel(false)
        executor.shutdown()
        timer.stop()
        if (printScreenEachCycle) {
            cpu.screen.printToConsole()
        }
    }

    fun awaitCompletion() {
        try {
            cpuFuture?.get()
        } catch (_: Exception) {
            // Expected once cpuFuture is cancelled in stop().
        }
    }
}
