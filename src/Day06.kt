import java.io.File

fun main() {

    fun parseInput(input: String): List<String> {
        return input.split("")
    }

    val fileName =
//        "src/Day06_sample.txt"
        "src/Day06_quiz.txt"
    val textInput = File(fileName).readText()
    val splitList = parseInput(textInput)

    val inputList = mutableListOf<String>()
    for (aList in splitList) {
        if (aList.isNotEmpty()) {
            inputList.add(aList)
        }
    }

    fun checkUniqueStringList(input: List<String>): Boolean {
        return input.count() == input.distinct().count()
    }

    fun part1Solution() {
        var findCount = 0
        for (i in 0 until inputList.size) {
            val findList = mutableListOf<String>()
            if (i + 4 < inputList.size) {
                for (index in i until i + 4) {
                    findList.add(inputList[index])
                }
                if (checkUniqueStringList(findList)) {
                    findCount = i + 4
                    break
                }
            }
            findList.clear()
        }

        println("Answer Part 1 : $findCount")
    }

    fun part2Solution() {
        var findCount = 0
        for (i in 0 until inputList.size) {
            val findList = mutableListOf<String>()
            if (i + 14 < inputList.size) {
                for (index in i until i + 14) {
                    findList.add(inputList[index])
                }
                if (checkUniqueStringList(findList)) {
                    findCount = i + 14
                    break
                }
            }
            findList.clear()
        }

        println("Answer Part 2 : $findCount")
    }

    part1Solution()
    part2Solution()


}