package d5700.instructions

import d5700.CPU
import d5700.Exception

class JumpInstruction(private val address: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        if (address % 2 != 0) {
            throw Exception("Cannot jump to address $address: not divisible by 2")
        }
        cpu.registers.p = address
    }

    override fun advancesProgramCounter(): Boolean = false
}
