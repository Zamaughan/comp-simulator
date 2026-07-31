package d5700.instructions

import d5700.CPU

/**
 * SKIP_NOT_EQUAL (9, rX, rY, 0) - compares rX and rY and skips the next
 * instruction (advances P by 4 instead of 2) if they're NOT equal.
 */
class SkipNotEqualInstruction(private val registerX: Int, private val registerY: Int) : Instruction() {

    private var shouldSkip = false

    override fun perform(cpu: CPU) {
        shouldSkip = cpu.registers.getGeneral(registerX) != cpu.registers.getGeneral(registerY)
    }

    override fun programCounterDelta(): Int = if (shouldSkip) 4 else 2
}
