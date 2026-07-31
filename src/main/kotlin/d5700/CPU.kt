package d5700

import d5700.instructions.InstructionFactory
import d5700.memory.Memory
import d5700.memory.RAM
import d5700.memory.ROM

class CPU(
    val registers: Registers = Registers(),
    val ram: Memory = RAM(),
    val rom: ROM = ROM(),
    val screen: Screen = Screen.instance,
    val keyboard: KeyboardInput = ConsoleKeyboardInput()
) {
    val activeMemory: Memory
        get() = if (registers.m) rom else ram

    fun step(): Boolean {
        if (registers.p !in 0 until rom.sizeInBytes - 1) {
            return false
        }

        val byte1 = rom.read(registers.p)
        val byte2 = rom.read(registers.p + 1)

        if (byte1 == 0 && byte2 == 0) {
            return false
        }

        val instruction = InstructionFactory.decode(byte1, byte2)
        instruction.execute(this)
        return true
    }
}
