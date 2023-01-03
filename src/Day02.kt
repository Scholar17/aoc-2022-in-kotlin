import java.io.File

fun main() {
    fun parseInput(input: String): List<String> {
        return input.split("\r").map { bit ->
            bit
        }
    }

    fun calculateWinOrLose(input: List<String>): Int {
        var myScoreMark = 0
        if (input.isNotEmpty()) {
            when (input.first()) {
                EnumValue.OpponentRock.representer -> {
                    when (input.last()) {
                        EnumValue.MyRock.representer -> {
                            myScoreMark = EnumValue.MyRock.value + 3
                        }

                        EnumValue.MyPaper.representer -> {
                            myScoreMark = EnumValue.MyPaper.value + 6
                        }

                        EnumValue.MyScissors.representer -> {
                            myScoreMark = EnumValue.MyScissors.value
                        }
                    }
                }

                EnumValue.OpponentPaper.representer -> {
                    when (input.last()) {
                        EnumValue.MyRock.representer -> {
                            myScoreMark = EnumValue.MyRock.value
                        }

                        EnumValue.MyPaper.representer -> {
                            myScoreMark = EnumValue.MyPaper.value + 3
                        }

                        EnumValue.MyScissors.representer -> {
                            myScoreMark = EnumValue.MyScissors.value + 6
                        }
                    }
                }

                EnumValue.OpponentScissors.representer -> {
                    when (input.last()) {
                        EnumValue.MyRock.representer -> {
                            myScoreMark = EnumValue.MyRock.value + 6
                        }

                        EnumValue.MyPaper.representer -> {
                            myScoreMark = EnumValue.MyPaper.value
                        }

                        EnumValue.MyScissors.representer -> {
                            myScoreMark = EnumValue.MyScissors.value + 3
                        }
                    }
                }
            }
        }
        return myScoreMark
    }

    fun changeDecisionToChoose(input: List<String>): List<String> {
        val modifiedPair = mutableListOf<String>()
        when (input.first()) {
            EnumValue.OpponentRock.representer -> {
                when (input.last()) {
                    EnumValue.MyRock.representer -> {
                        modifiedPair.add(EnumValue.OpponentRock.representer)
                        modifiedPair.add(EnumValue.MyScissors.representer)
                    }

                    EnumValue.MyPaper.representer -> {
                        modifiedPair.add(EnumValue.OpponentRock.representer)
                        modifiedPair.add(EnumValue.MyRock.representer)
                    }

                    EnumValue.MyScissors.representer -> {
                        modifiedPair.add(EnumValue.OpponentRock.representer)
                        modifiedPair.add(EnumValue.MyPaper.representer)
                    }
                }
            }

            EnumValue.OpponentPaper.representer -> {
                when (input.last()) {
                    EnumValue.MyRock.representer -> {
                        modifiedPair.add(EnumValue.OpponentPaper.representer)
                        modifiedPair.add(EnumValue.MyRock.representer)
                    }

                    EnumValue.MyPaper.representer -> {
                        modifiedPair.add(EnumValue.OpponentPaper.representer)
                        modifiedPair.add(EnumValue.MyPaper.representer)
                    }

                    EnumValue.MyScissors.representer -> {
                        modifiedPair.add(EnumValue.OpponentPaper.representer)
                        modifiedPair.add(EnumValue.MyScissors.representer)
                    }
                }
            }

            EnumValue.OpponentScissors.representer -> {
                when (input.last()) {
                    EnumValue.MyRock.representer -> {
                        modifiedPair.add(EnumValue.OpponentScissors.representer)
                        modifiedPair.add(EnumValue.MyPaper.representer)
                    }

                    EnumValue.MyPaper.representer -> {
                        modifiedPair.add(EnumValue.OpponentScissors.representer)
                        modifiedPair.add(EnumValue.MyScissors.representer)
                    }

                    EnumValue.MyScissors.representer -> {
                        modifiedPair.add(EnumValue.OpponentScissors.representer)
                        modifiedPair.add(EnumValue.MyRock.representer)
                    }
                }
            }
        }
        return modifiedPair
    }


    val filename =
//        "src/Day02_sample.txt"
        "src/Day02_quiz.txt"
    val testInput = File(filename).readText()
    val inputList = parseInput(input = testInput)
    val nestedListOriginal = mutableListOf<List<String>>()
    val nestedListModified = mutableListOf<List<String>>()
    for (aList in inputList) {
        nestedListOriginal.add(aList.trim().split(" "))
    }

    var scorePart1 = 0
    for (aList in nestedListOriginal) {
        scorePart1 += calculateWinOrLose(aList)
    }

    var scorePart2 = 0
    for (aList in nestedListOriginal) {
        nestedListModified.add(changeDecisionToChoose(aList))
    }

    for (aList in nestedListModified) {
        scorePart2 += calculateWinOrLose(aList)
    }
    println(nestedListOriginal)
    println(nestedListModified)
    println(scorePart1)
    println(scorePart2)
}