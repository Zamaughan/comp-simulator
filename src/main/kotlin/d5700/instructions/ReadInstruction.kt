package d5700.instructions

import d5700.CPU

class ReadInstruction(private val registerX: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        val value = cpu.activeMemory.read(cpu.registers.a)
        cpu.registers.setGeneral(registerX, value)
    }
}
