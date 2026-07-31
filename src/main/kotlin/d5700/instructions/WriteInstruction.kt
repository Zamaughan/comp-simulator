package d5700.instructions

import d5700.CPU

class WriteInstruction(private val registerX: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.activeMemory.write(cpu.registers.a, cpu.registers.getGeneral(registerX))
    }
}
