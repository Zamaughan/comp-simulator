package d5700.memory

import d5700.Exception

class ROM(override val sizeInBytes: Int = 4096, private val isWritable: Boolean = false) : Memory {

    private val bytes = IntArray(sizeInBytes)

    fun load(program: ByteArray) {
        if (program.size > sizeInBytes) {
            throw Exception("Program is too large for a ${sizeInBytes}-byte ROM chip")
        }
        for (i in program.indices) {
            bytes[i] = program[i].toInt() and 0xFF
        }
        for (i in program.size until sizeInBytes) {
            bytes[i] = 0
        }
    }

    override fun read(address: Int): Int {
        checkBounds(address)
        return bytes[address] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        checkBounds(address)
        if (!isWritable) {
            throw Exception("Cannot write to ROM: this cartridge's chip is read-only")
        }
        bytes[address] = value and 0xFF
    }

    private fun checkBounds(address: Int) {
        if (address !in 0 until sizeInBytes) {
            throw Exception("ROM address out of bounds: $address")
        }
    }
}
