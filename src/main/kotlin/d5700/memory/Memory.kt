package d5700.memory

interface Memory {
    val sizeInBytes: Int

    fun read(address: Int): Int

    fun write(address: Int, value: Int)
}
