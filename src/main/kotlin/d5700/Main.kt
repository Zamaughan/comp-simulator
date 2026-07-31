package d5700

fun main() {
    print("Enter the path to a D5700 program to load: ")
    val path = readln().trim()

    val computer = Computer()
    computer.loadProgram(path)
    computer.start()
    computer.awaitCompletion()
}
