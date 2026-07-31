package d5700.instructions

import d5700.Exception

object InstructionFactory {

    fun decode(byte1: Int, byte2: Int): Instruction {
        val opcode = (byte1 shr 4) and 0xF
        val n1 = byte1 and 0xF
        val n2 = (byte2 shr 4) and 0xF
        val n3 = byte2 and 0xF

        return when (opcode) {
            0x0 -> StoreInstruction(registerX = n1, byteValue = byte2)
            0x1 -> AddInstruction(registerX = n1, registerY = n2, registerZ = n3)
            0x2 -> SubInstruction(registerX = n1, registerY = n2, registerZ = n3)
            0x3 -> ReadInstruction(registerX = n1)
            0x4 -> WriteInstruction(registerX = n1)
            0x5 -> JumpInstruction(address = (n1 shl 8) or byte2)
            0x6 -> ReadKeyboardInstruction(registerX = n1)
            0x7 -> SwitchMemoryInstruction()
            0x8 -> SkipEqualInstruction(registerX = n1, registerY = n2)
            0x9 -> SkipNotEqualInstruction(registerX = n1, registerY = n2)
            0xA -> SetAInstruction(address = (n1 shl 8) or byte2)
            0xB -> SetTInstruction(byteValue = (n1 shl 4) or n2)
            0xC -> ReadTInstruction(registerX = n1)
            0xD -> ConvertToBase10Instruction(registerX = n1)
            0xE -> ConvertByteToAsciiInstruction(registerX = n1, registerY = n2)
            0xF -> DrawInstruction(registerX = n1, row = n2, column = n3)
            else -> throw Exception("Unknown opcode: $opcode")
        }
    }
}
