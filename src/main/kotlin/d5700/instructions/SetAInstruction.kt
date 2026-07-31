package d5700.instructions

import d5700.CPU

class SetAInstruction(private val address: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.registers.a = address
    }
}
