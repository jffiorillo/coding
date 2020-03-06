package io

//http://blog.gainlo.co/index.php/2016/04/08/if-a-string-contains-an-anagram-of-another-string/

class CheckAnagram {

  private fun validateMaps(map: Map<Char, Int>, map1: Map<Char, Int>) =
      map.keys == map1.keys && map.entries.fold(true) { acc, (char, value) -> acc && map1[char] == value }

  fun execute(anagram: String, string: String): String? {
    val map = HashMap<Char, Int>()
    anagram.map { map.put(it, map.getOrDefault(it, 0) + 1) }

    var begin = 0
    val variableMap = HashMap<Char, Int>()
    string.forEachIndexed { index, value ->
      if (map.contains(value)) {
        variableMap[value] = variableMap.getOrDefault(value, 0) + 1
        if (index - begin == anagram.length) {
          if (validateMaps(map, variableMap)) {
            return string.substring(begin, index + 1)
          } else {
            begin = index
            variableMap.clear()
          }
        }
      } else {
        begin = index
        variableMap.clear()
      }
    }
    return null
  }
}


fun main() {
  val checkAnagram = CheckAnagram()

  val result = checkAnagram.execute("weivretni", "coding questions interview")

  println("Result ${result}")
}