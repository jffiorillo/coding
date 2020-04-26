package io.dp

import io.utils.runTests

// https://ianna1009.gitbooks.io/leectcode/Cracking%20Coding%20Interview/Chapter8.%20Recursion%20and%20Dynamic%20Programming/81_triple_step.html
class ClimbingStairsTriple {

  fun execute(stairs : Int): Int = when (stairs){
    0 -> 0
    1 -> 1
    2 -> 2
    else -> {
      var n3 = 1
      var n2 = 2
      var result = 4
      repeat(stairs -3){
        val current= n3+n2+result
        n3 = n2
        n2 = result
        result = current
      }
      result
    }
  }
}

fun main(){
  runTests(listOf(
      0 to 0,
      1 to 1,
      2 to 2,
      3 to 4,
      4 to 7,
      5 to 13
  )){(input,value) -> value to ClimbingStairsTriple().execute(input)}
}