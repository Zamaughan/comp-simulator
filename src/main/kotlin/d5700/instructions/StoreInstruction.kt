package d5700.instructions

import d5700.CPU

class StoreInstruction(private val registerX: Int, private val byteValue: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.registers.setGeneral(registerX, byteValue)
    }
}
