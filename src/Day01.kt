import java.io.File

fun main() {
    fun parseInput(input: String): List<List<Int>> {
       return input.split("\n\r").map { elf ->
            elf.lines().map{
                it.toIntOrNull() ?: 0
            }
        }
    }
    fun part1(input: String): Int {
        val data: List<List<Int>> = parseInput(input)
        return data.maxOf{
            it.sum()
        }
    }

    fun part2(input: String): Int {
        val data = parseInput(input)
        return data.map {
            it.sum()
        }.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/Day01_test.txt").readText()
//    check(part1(testInput) == 24000)

//    val input = readInput("Day01")
    part1(testInput).println()
    part2(testInput).println()
//    part2(input).println()
}
