package d5700.instructions

import d5700.CPU


class ReadKeyboardInstruction(private val registerX: Int) : Instruction() {
    override fun perform(cpu: CPU) {
        val rawInput = cpu.keyboard.readLineOfInput().trim()
        val hexDigits = rawInput.filter { it.isDigit() || it.uppercaseChar() in 'A'..'F' }
        val truncated = hexDigits.take(2)
        val value = if (truncated.isEmpty()) 0 else truncated.toInt(16)
        cpu.registers.setGeneral(registerX, value)
    }
}
