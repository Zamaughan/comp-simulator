package d5700.instructions

import d5700.CPU
import d5700.Exception

/**
 * DRAW (F, rX, rY, rZ) - draws the ASCII character for the byte in rX at
 * row rY, column rZ. Terminates the program with an error if rX holds a
 * value greater than 0x7F.
 *
 * Note: despite the rY/rZ naming, the row and column are the literal
 * nibble values from the instruction itself, not lookups into registers
 * rY/rZ. Tracing hello.out (F000, F101, F202, F303, F404 drawing H-E-L-L-O
 * across columns 0-4) and hello_from_rom.out against their expected output
 * confirms this - if rY/rZ were register lookups, most of those draws
 * would target rows/columns far outside the 8x8 screen.
 */
class DrawInstruction(
    private val registerX: Int,
    private val row: Int,
    private val column: Int
) : Instruction() {
    override fun perform(cpu: CPU) {
        val character = cpu.registers.getGeneral(registerX)
        if (character > 0x7F) {
            throw Exception("Cannot draw $character: not a valid ASCII character (> 0x7F)")
        }
        cpu.screen.draw(row, column, character)
    }
}
