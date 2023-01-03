import java.io.File

fun main() {

    fun parseInputCommaAndNewLine(input: String): List<String> {
        return input.split(",", "\r\n")
    }

    fun parseInputDash(input: String): List<Int> {
        return input.split("-").map { str ->
            str.toInt()
        }
    }

    val fileName =
        "src/Day04_sample.txt"
//        "src/Day04_quiz.txt"
    val input = File(fileName).readText()
    val parseComma = parseInputCommaAndNewLine(input)
    val parseCommaList = parseComma.chunked(1)
    val parseDashList = mutableListOf<List<Int>>()
    for (aList in parseCommaList) {
        for (element in aList) {
            parseDashList.add(parseInputDash(element))
        }
    }

    fun comparedList(input: List<Int>): List<Int> {
        val pairValue = mutableListOf<Int>()
        if (input.first() == input.last()) {
            pairValue.add(input.first())
        } else if (input.first() < input.last()) {
            for (i in input.first() until input.last() + 1) {
                pairValue.add(i)
            }
        }
        return pairValue
    }

    val modifiedList = mutableListOf<List<Int>>()
    for (aList in parseDashList) {
        modifiedList.add(comparedList(aList))
    }

    fun checkContain(input: List<List<Int>>): Boolean {
        var isContain = false
        for (i in input.indices - 1) {
            if (input[i].containsAll(input[i + 1])) {
                isContain = true
            } else if (input[i + 1].containsAll(input[i])) {
                isContain = true
            }
        }
        return isContain
    }

    fun checkOverlap(input: List<List<Int>>): Boolean {
        var isOverlap = false
        for (i in input.indices - 1) {
            if (input[i].intersect(input[i + 1].toSet()).isNotEmpty()) {
                isOverlap = true
            }
        }
        return isOverlap
    }

    val compareListPart1 = modifiedList.chunked(2)
    val compareListPart2 = modifiedList.chunked(2)

    var part1Count = 0
    var part2Count = 0

    for (aList in compareListPart1) {
        if (checkContain(aList)) {
            part1Count++
        }
    }

    for (aList in compareListPart2) {
        if (checkOverlap(aList)) {
            part2Count++
        }
    }

    println(compareListPart1)
    println(part1Count)

    println(compareListPart2)
    println(part2Count)

}