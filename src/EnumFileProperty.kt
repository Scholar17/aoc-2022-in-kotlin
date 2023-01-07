
data class FileProperty(
    var directory :String ?="",
    var subDirectory: MutableList<String> ?= mutableListOf()
, var totalSize: Int ?= 0
, var subSize: Int ?=0 )