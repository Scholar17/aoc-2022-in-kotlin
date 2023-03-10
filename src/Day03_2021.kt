import java.io.File

fun main() {
    fun parseInput(input: String): List<List<Int>> {
        return input.split("\n").map { bit ->
            bit.map {
                it.digitToIntOrNull() ?: 0
            }
        }
    }


    val filename =
        "src/Day03_sample_2021.txt"
//        "src/Day03_quiz_2021.txt"
    val textInput = File(filename).readText()
    val nestedIntList = parseInput(textInput)
    println(nestedIntList)

    fun maxCounter(input: List<Int>): Int {
        return input
            .groupingBy { it }
            .eachCount()
            .maxBy { it.value }.key
    }

    fun maxCounterValue(input: List<Int>): Int {
        return input
            .groupingBy { it }
            .eachCount()
            .maxBy { it.value }.value
    }

    fun minCounter(input: List<Int>): Int {
        return input
            .groupingBy { it }
            .eachCount()
            .minBy { it.value }.key
    }

    val filterTotalResult = mutableListOf<List<Int>>()

    for (i in 0 until nestedIntList.first().size - 1) {
        //for each column to list
        val columnList = nestedIntList.map { it[i] }
        filterTotalResult.add(columnList)
        println(columnList)
    }

    val gammaRate = mutableListOf<Int>()
    val epsilonRate = mutableListOf<Int>()
    val test = mutableListOf<Int>()

    for (aList in filterTotalResult) {
        gammaRate.add(maxCounter(aList))
        epsilonRate.add(minCounter(aList))
        test.add(maxCounterValue(aList))
    }

    println(test)
    var gammaRateInString = ""
    var epsilonRateInString = ""


    for (s in gammaRate) {
        gammaRateInString += s
    }

    for (s in epsilonRate) {
        epsilonRateInString += s
    }


    println(gammaRateInString)
    println(epsilonRateInString)


    val gammaRateInInteger = gammaRateInString.toInt(2)
    val epsilonRateInInteger = epsilonRateInString.toInt(2)
    println(gammaRateInInteger)
    println(epsilonRateInInteger)
    val powerConsumption = gammaRateInInteger * epsilonRateInInteger
    println(powerConsumption)
}
