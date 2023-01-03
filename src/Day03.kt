import java.io.File
import kotlin.math.absoluteValue

fun main() {

    fun parseInput(input: String): List<String> {
        return input.split("\r\n").map { value ->
            value
        }
    }

    val fileName =
//        "src/Day03_sample.txt"
    "src/Day03_quiz.txt"
    val inputText = File(fileName).readText()
    val inputList = parseInput(input = inputText)

    println(inputList)
    println()
    val part1ChunkList = mutableListOf<List<String>>()

    for (aList in inputList) {
        part1ChunkList.add(aList.chunked(aList.length / 2))
    }

    var part2ChunkList: List<List<String>> = inputList.chunked(3)

    println(part2ChunkList)


    fun findCommonWord(inputList: List<String>): String {
        val first = inputList.first().toCharArray()
        val second = inputList.last().toCharArray()
        var index = ""

        for (aList in first) {
            if (second.contains(aList)) {
                index = aList.toString()
            }
        }
        return index
    }


    fun findCommonWordPart2(inputList: List<String>): String {

        println(inputList)
        val result = inputList.map {
            it.chunked(1)
        }

        var commonResult = ""
        for (aList in result) {
            for (element in aList) {
                if (result.all {
                        it.contains(element)
                    })
                    commonResult = element
            }
        }
        println("result")
        println(result)
        return commonResult
    }


    val commonResultList = mutableListOf<String>()
    for (aList in part2ChunkList) {
        commonResultList.add(findCommonWordPart2(aList))
    }
    println(commonResultList)

    val resultList = mutableListOf<String>()
    for (aList in part1ChunkList) {
        resultList.add(findCommonWord(aList))
    }

    println()
//    println(resultList)

    fun findNumericPosition(input: String): Int {
        val charArray = input.toCharArray()
        var position = 0
        for (aList in charArray) {
            val temp = aList.code
            val lowerMinus = 96 //for lower case
            val upperMinus = 38 //for upper case
            position = if ((temp <= 122) and (temp >= 97)) temp - lowerMinus else temp - upperMinus
        }
        return position
    }

    var sumNumberPart1 = 0
    for (aList in resultList) {
        sumNumberPart1 += findNumericPosition(aList)
    }

    println(sumNumberPart1)

    var sumNumberPart2 = 0
    for (aList in commonResultList) {
        sumNumberPart2 += findNumericPosition(aList)
    }
    println(sumNumberPart2)


}