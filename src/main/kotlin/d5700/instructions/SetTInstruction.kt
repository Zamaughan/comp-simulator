package d5700.instructions

import d5700.CPU

class SetTInstruction(private val byteValue: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.registers.t = byteValue
    }
}
