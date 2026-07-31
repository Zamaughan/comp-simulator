package d5700.instructions

import d5700.CPU

class SubInstruction(
    private val registerX: Int,
    private val registerY: Int,
    private val registerZ: Int
) : Instruction() {
    override fun perform(cpu: CPU) {
        val difference = cpu.registers.getGeneral(registerX) - cpu.registers.getGeneral(registerY)
        cpu.registers.setGeneral(registerZ, difference)
    }
}
