package d5700.instructions

import d5700.CPU
import d5700.Exception

class ConvertByteToAsciiInstruction(private val registerX: Int, private val registerY: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        val digit = cpu.registers.getGeneral(registerX)
        if (digit > 0xF) {
            throw Exception("Cannot convert $digit to ASCII: not a single hex digit (> 0xF)")
        }
        val asciiCode = if (digit < 10) {
            '0'.code + digit
        } else {
            'A'.code + (digit - 10)
        }
        cpu.registers.setGeneral(registerY, asciiCode)
    }
}
