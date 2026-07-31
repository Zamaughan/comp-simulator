package d5700.instructions

import d5700.CPU

/** ADD (1, rX, rY, rZ) - adds rX + rY and stores the result in rZ. */
class AddInstruction(
    private val registerX: Int,
    private val registerY: Int,
    private val registerZ: Int
) : Instruction() {
    override fun perform(cpu: CPU) {
        val sum = cpu.registers.getGeneral(registerX) + cpu.registers.getGeneral(registerY)
        cpu.registers.setGeneral(registerZ, sum)
    }
}
