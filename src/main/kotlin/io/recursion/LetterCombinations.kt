package io.recursion

// https://leetcode.com/explore/learn/card/recursion-ii/507/beyond-recursion/2905/
class LetterCombinations {

  val map = mapOf(
      '2' to listOf("a","b","c"),
      '3' to listOf("d","e","f"),
      '4' to listOf("g","h","i"),
      '5' to listOf("j","k","l"),
      '6' to listOf("m","n","o"),
      '7' to listOf("p","q","r","s"),
      '8' to listOf("t","u","v"),
      '9' to listOf("w","x","y","z")
  )

  fun execute(digits:String) : List<String> = when(digits.length){
    0 -> emptyList()
    1 -> map.getValue(digits.first())
    else -> {
      map.getValue(digits.first()).flatMap { letter->
        execute(digits.removePrefix(digits.first().toString())).map {
          letter + it
        }
      }
    }
  }
}

fun main(){
  val letterCombinations = LetterCombinations()
  letterCombinations.execute("23").map { println(it) }
}