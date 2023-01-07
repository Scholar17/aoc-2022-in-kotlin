import java.io.File

fun main() {
    val fileName = "src/Day07_sample.txt"
    val inputList = File(fileName).readLines()
    println(inputList)


    val saveList = mutableListOf<FileProperty>()
    for (aList in inputList) {
        if (aList.contains("cd") && !aList.contains(".")) {
            saveList.add(FileProperty(directory = aList.split("cd", " ").last()))
        } else if(aList.contains("dir")) {
            saveList.last().subDirectory?.add(aList.split("dir", " ").last())
        } else if(aList.contains("ls")) {

        } else if(aList.contains("$")){
        } else {
            saveList.last().subSize = saveList.last().subSize?.plus(aList.split(" ").first().toInt())
        }
    }



    println(saveList)



    for (aList in saveList) {
        if (aList.subDirectory!!.isEmpty()) {
            aList.totalSize = aList.subSize
        }
    }


 println(saveList)

    for (aList in saveList) {

    }


    fun findSubDirectorySize(): Int {
      return 0
    }

}