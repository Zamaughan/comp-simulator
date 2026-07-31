package d5700.instructions

import d5700.CPU

class ReadTInstruction(private val registerX: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.registers.setGeneral(registerX, cpu.registers.t)
    }
}
