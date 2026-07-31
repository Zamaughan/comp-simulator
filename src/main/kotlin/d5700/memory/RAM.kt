package d5700.memory

import d5700.Exception

class RAM(override val sizeInBytes: Int = 4096) : Memory {

    private val bytes = IntArray(sizeInBytes)

    override fun read(address: Int): Int {
        checkBounds(address)
        return bytes[address] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        checkBounds(address)
        bytes[address] = value and 0xFF
    }

    private fun checkBounds(address: Int) {
        if (address !in 0 until sizeInBytes) {
            throw Exception("RAM address out of bounds: $address")
        }
    }
}
