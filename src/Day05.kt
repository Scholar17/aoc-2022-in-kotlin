import java.io.File

fun main() {
    fun parseInputStack(input: List<String>): List<String> {
        return input.takeWhile {
            it.contains("[")
        }
    }

    fun parseInputInstruction(input: List<String>): List<String> {
        return input.dropWhile {
            !it.startsWith("move")
        }
    }

    val fileName =
//        "src/Day05_sample.txt"
        "src/Day05_quiz.txt"
    val text = File(fileName).readLines()
    val stackList = parseInputStack(input = text)
    val instructionList = parseInputInstruction(input = text)
    val spaceAddedList = mutableListOf<List<String>>()
    for (aList in stackList) {
        spaceAddedList.add(aList.replace("\\s".toRegex(), "[ ]").chunked(3))
    }

    var count = 0
    val map: MutableMap<Int, MutableList<String>> = mutableMapOf()

    fun insertToMultiMap(
        map: MutableMap<Int, MutableList<String>>, key: Int,
        value: String
    ) {
        if (map.containsKey(key)) {
            map[key]!!.add(value)
        } else {
            map[key] = mutableListOf()
            map[key]!!.add(value)
        }
    }

    fun insertListToMultiMapPart1(
        map: MutableMap<Int, MutableList<String>>, key: Int,
        value: MutableList<String>
    ) {
        if (map.containsKey(key)) {
            map[key]!!.addAll(0, value.reversed())
        } else {
            map[key] = value
        }
    }

    fun insertListToMultiMapPart2(
        map: MutableMap<Int, MutableList<String>>, key: Int,
        value: MutableList<String>
    ) {
        if (map.containsKey(key)) {
            map[key]!!.addAll(0, value)
        } else {
            map[key] = value
        }
    }

    fun removeListToMultiMap(
        map: MutableMap<Int, MutableList<String>>, key: Int, count: Int
    ) {
        if (map.containsKey(key)) {
            for (index in 0 until count) {
                map[key]!!.removeAt(0)
            }
        } else {

        }
    }


    fun doStackPart1() {
        for (aList in spaceAddedList) {
            var newCount = 0
            for (element in aList) {
                if (element == "[ ]") {
                    count++
                } else {
                    when (count) {
                        0 -> {
                            insertToMultiMap(map, 0, element)
                            count = 0
                        }

                        1 -> {
                            insertToMultiMap(map, newCount + 1, element)
                            count = 0
                            newCount++
                        }

                        2 -> {
                            insertToMultiMap(map, newCount, element)
                            count = 0
                            newCount++
                        }

                        3 -> {
                            insertToMultiMap(map, newCount, element)
                            count = 0
                            newCount++
                        }

                        else -> {
                            if (count % 4 == 0) {
                                insertToMultiMap(map, count / 4, element)
                                newCount = count / 4
                                count = 0
                            } else {
                                insertToMultiMap(map, (count / 4) + (count % 4) + newCount, element)
                                newCount += (count / 4) + (count % 4)
                                count = 0
                            }
                        }
                    }
                }
            }
        }
    }

    fun doStackPart2() {
        map.clear()
        for (aList in spaceAddedList) {
            var newCount = 0
            for (element in aList) {
                if (element == "[ ]") {
                    count++
                } else {
                    when (count) {
                        0 -> {
                            insertToMultiMap(map, 0, element)
                            count = 0
                        }

                        1 -> {
                            insertToMultiMap(map, newCount + 1, element)
                            count = 0
                            newCount++
                        }

                        2 -> {
                            insertToMultiMap(map, newCount, element)
                            count = 0
                            newCount++
                        }

                        3 -> {
                            insertToMultiMap(map, newCount, element)
                            count = 0
                            newCount++
                        }

                        else -> {
                            if (count % 4 == 0) {
                                insertToMultiMap(map, count / 4, element)
                                newCount = count / 4
                                count = 0
                            } else {
                                insertToMultiMap(map, (count / 4) + (count % 4) + newCount, element)
                                newCount += (count / 4) + (count % 4)
                                count = 0
                            }
                        }
                    }
                }
            }
        }
    }

    //    move n from n to n
    fun movePart1(count: Int, from: Int, to: Int) {
        val listFrom = map[from]
        val listToAdd = listFrom!!.take(count).toMutableList()
        insertListToMultiMapPart1(map, to, listToAdd)
        removeListToMultiMap(map, from, count)
    }

    fun movePart2(count: Int, from: Int, to: Int) {
        val listFrom = map[from]
        val listToAdd = listFrom!!.take(count).toMutableList()
        insertListToMultiMapPart2(map, to, listToAdd)
        removeListToMultiMap(map, from, count)
    }

    fun doInstructionPart1() {
        for (aList in instructionList) {
            val (_, amount, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex().find(aList)!!.groupValues
            movePart1(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
        }
    }

    fun doInstructionPart2() {
        for (aList in instructionList) {
            val (_, amount, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex().find(aList)!!.groupValues
            movePart2(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
        }
    }

    fun getLastCharacter(): MutableList<String> {
        val lastChar = mutableListOf<String>()
        val sortedMap = map.toList().sortedBy { (key, _) ->
            key
        }.toMap()
        sortedMap.forEach {
            if (it.value.isNotEmpty()) {
                lastChar.add(it.value.first())
            }
        }
        return lastChar
    }


    fun part1() {
        doStackPart1()
        doInstructionPart1()
        println("Part1 Answer")
        for (item in getLastCharacter()) {
            print(item.filter { it != '[' && it != ']' })
        }
    }

    fun part2() {
        doStackPart2()
        doInstructionPart2()
        println()
        println("Part2 Answer")
        for (item in getLastCharacter()) {
            print(item.filter { it != '[' && it != ']' })
        }
    }

    part1()
    part2()


}