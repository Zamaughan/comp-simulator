package d5700

/**
 * Holds the D5700's register file:
 *  - 8 general purpose 8-bit registers, r0-r7
 *  - P: 16-bit program counter
 *  - T: 8-bit timer register
 *  - A: 16-bit address register
 *  - M: 1-bit memory flag (false = RAM, true = ROM)
 *
 * All the general purpose registers and T are masked to a single byte (0-255)
 * on every write so callers never have to worry about overflow themselves.
 */
class Registers {

    private val generalPurpose = IntArray(8)

    var p: Int = 0
        set(value) {
            field = value and 0xFFFF
        }

    var t: Int = 0
        set(value) {
            field = value and 0xFF
        }

    var a: Int = 0
        set(value) {
            field = value and 0xFFFF
        }

    var m: Boolean = false

    fun getGeneral(register: Int): Int {
        requireValidRegister(register)
        return generalPurpose[register]
    }

    fun setGeneral(register: Int, value: Int) {
        requireValidRegister(register)
        generalPurpose[register] = value and 0xFF
    }

    private fun requireValidRegister(register: Int) {
        require(register in 0..7) { "Invalid register index: $register" }
    }
}
