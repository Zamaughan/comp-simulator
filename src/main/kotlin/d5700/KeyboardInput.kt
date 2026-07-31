package d5700

interface KeyboardInput {
    fun readLineOfInput(): String
}

class ConsoleKeyboardInput : KeyboardInput {
    override fun readLineOfInput(): String = readln() ?: ""
}
