package d5700

class Screen private constructor() {

    companion object {
        const val ROWS = 8
        const val COLUMNS = 8

        val instance: Screen by lazy { Screen() }
    }

    private val frameBuffer = Array(ROWS) { CharArray(COLUMNS) { ' ' } }

    fun draw(row: Int, column: Int, character: Int) {
        if (character > 0x7F) {
            throw Exception("Cannot draw byte $character: not a valid ASCII character (> 0x7F)")
        }
        require(row in 0 until ROWS) { "Row out of bounds: $row" }
        require(column in 0 until COLUMNS) { "Column out of bounds: $column" }
        frameBuffer[row][column] = character.toChar()
    }

    fun charAt(row: Int, column: Int): Char = frameBuffer[row][column]

    fun render(): String = frameBuffer.joinToString("\n") { row -> String(row) }

    fun printToConsole() {
        println("+" + "-".repeat(COLUMNS) + "+")
        for (row in frameBuffer) {
            println("|" + String(row) + "|")
        }
        println("+" + "-".repeat(COLUMNS) + "+")
    }

    /** Clears the frame buffer. Mostly useful so unit tests don't leak state into each other. */
    fun reset() {
        for (row in frameBuffer) {
            row.fill(' ')
        }
    }
}
