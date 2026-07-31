package d5700.instructions

import d5700.CPU

class SwitchMemoryInstruction : Instruction() {
    override fun perform(cpu: CPU) {
        cpu.registers.m = !cpu.registers.m
    }
}
