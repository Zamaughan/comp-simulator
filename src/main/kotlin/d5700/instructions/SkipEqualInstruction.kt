package d5700.instructions

import d5700.CPU

class SkipEqualInstruction(private val registerX: Int, private val registerY: Int) : Instruction() {

    private var shouldSkip = false

    override fun perform(cpu: CPU) {
        shouldSkip = cpu.registers.getGeneral(registerX) == cpu.registers.getGeneral(registerY)
    }

    override fun programCounterDelta(): Int = if (shouldSkip) 4 else 2
}
