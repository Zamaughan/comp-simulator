package d5700

/** Test double for [KeyboardInput] that hands back a queue of canned lines. */
class FakeKeyboardInput(private val lines: MutableList<String>) : KeyboardInput {
    constructor(vararg lines: String) : this(lines.toMutableList())

    override fun readLineOfInput(): String {
        return if (lines.isEmpty()) "" else lines.removeAt(0)
    }
}
