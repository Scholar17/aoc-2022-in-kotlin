import java.io.File

fun main() {
    fun parseInput(input: String): List<String> {
        return input.split("\r").map { bit ->
            bit
        }
    }

    fun calculateWinOrLose(input: List<String>): Int {
        var myScoreMark = 0
        when (input.first()) {
            Score.OpponentRock.representer -> {
                when (input.last()) {
                    Score.MyRock.representer -> {
                        myScoreMark = Score.MyRock.value +3
                    }

                    Score.MyPaper.representer -> {
                        myScoreMark = Score.MyPaper.value +6
                    }

                    Score.MyScissors.representer -> {
                        myScoreMark = Score.MyScissors.value
                    }
                }
            }

            Score.OpponentPaper.representer -> {
                when (input.last()) {
                    Score.MyRock.representer -> {
                        myScoreMark = Score.MyRock.value
                    }

                    Score.MyPaper.representer -> {
                        myScoreMark = Score.MyPaper.value + 3
                    }

                    Score.MyScissors.representer -> {
                        myScoreMark = Score.MyScissors.value + 6
                    }
                }
            }

            Score.OpponentScissors.representer -> {
                when (input.last()) {
                    Score.MyRock.representer -> {
                        myScoreMark = Score.MyRock.value + 6
                    }

                    Score.MyPaper.representer -> {
                        myScoreMark = Score.MyPaper.value
                    }

                    Score.MyScissors.representer -> {
                        myScoreMark = Score.MyScissors.value + 3
                    }
                }
            }
        }
        return myScoreMark
    }

    val filename =
//        "src/Day02_sample.txt"
        "src/Day02_quiz.txt"
    val testInput = File(filename).readText()
    val inputList = parseInput(input = testInput)
    var nestedList = mutableListOf<List<String>>()
    for (aList in inputList) {
        nestedList.add(aList.trim().split(" "))
    }
    println(nestedList)

    var score = 0
    for(aList in nestedList) {
        score += calculateWinOrLose(aList)
    }
    println(score)
}