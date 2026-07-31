package d5700.instructions

import d5700.CPU

/**
 * Every instruction goes through the same three steps every time it
 * runs: (1) it does its own operation against the CPU's registers/memory,
 * (2) it decides whether the program counter should move on its own, and
 * (3) if so, the program counter is advanced by however many bytes that
 * instruction accounts for.
 */
abstract class Instruction {
    /** Must not overwrite in subclasses **/
    fun execute(cpu: CPU) {
        perform(cpu)
        if (advancesProgramCounter()) {
            cpu.registers.p += programCounterDelta()
        }
    }

    /** The operation this instruction performs against the CPU. */
    protected abstract fun perform(cpu: CPU)

    protected open fun advancesProgramCounter(): Boolean = true

    protected open fun programCounterDelta(): Int = 2
}
