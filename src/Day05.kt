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
    println(instructionList)
    println(stackList)
    val spaceAddedList = mutableListOf<List<String>>()
    for (aList in stackList) {
        spaceAddedList.add(aList.replace("\\s".toRegex(), "[ ]").chunked(3))
    }
    println(spaceAddedList)


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

    fun insertListToMultiMap(
        map: MutableMap<Int, MutableList<String>>, key: Int,
        value: MutableList<String>
    ) {
        if (map.containsKey(key)) {
            map[key]!!.addAll(0,value.reversed())
        } else {
            map[key] = value
        }
    }

    fun removeListToMultiMap(
        map: MutableMap<Int, MutableList<String>>, key: Int,
        value: MutableList<String>
    ) {
        if (map.containsKey(key)) {
            map[key]!!.removeAll(value)
        } else {

        }
    }


    fun doStack() {
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

    //    move 2 from 1 to 0
    fun move(count: Int, from: Int, to: Int) {
        val tempList = map[from]
        println("Temp List")
        println(tempList)
        val temp = tempList!!.take(count).toMutableList()
        insertListToMultiMap(map, to, temp)
        removeListToMultiMap(map, from, temp)
    }

    fun doInstruction() {
        for (aList in instructionList) {
                val (_, amount, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex().find(aList)!!.groupValues
            println("move${amount.toInt()}  from ${from.toInt() - 1}  to ${to.toInt() - 1} ")
                move(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
            map.forEach { (k, v) ->
                println("Key = ${k+1}, Value = $v")
            }
        }
    }

    fun getLastCharacter() : MutableList<String>{
        val lastChar = mutableListOf<String>()
        val sortedMap = map.toList().sortedBy {
            (key, _) -> key
        }.toMap()
        sortedMap.forEach{
            if(it.value.isNotEmpty()) {
                lastChar.add(it.value.first())
            }
        }
        return lastChar
    }

    doStack()
    map.forEach { (k, v) ->
        println("Key = ${k+1}, Value = $v")
    }
    doInstruction()
    println(getLastCharacter())
}