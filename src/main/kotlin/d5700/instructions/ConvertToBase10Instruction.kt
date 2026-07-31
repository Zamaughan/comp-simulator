package d5700.instructions

import d5700.CPU

/**
 * CONVERT_TO_BASE_10 (D, rX, 00) - converts the byte in rX to base 10 and
 * stores the 100s digit at address A, the 10s digit at A+1, and the 1s
 * digit at A+2. Writes go through the same active memory (RAM or ROM,
 * per M) as the WRITE instruction.
 */
class ConvertToBase10Instruction(private val registerX: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        val value = cpu.registers.getGeneral(registerX)
        val hundreds = (value / 100) % 10
        val tens = (value / 10) % 10
        val ones = value % 10

        val memory = cpu.activeMemory
        val address = cpu.registers.a
        memory.write(address, hundreds)
        memory.write(address + 1, tens)
        memory.write(address + 2, ones)
    }
}
