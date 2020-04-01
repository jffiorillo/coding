package io.array

// https://leetcode.com/problems/check-if-n-and-its-double-exist/
class CheckIfDoubleExist {
  fun execute(input: IntArray) =
      input.foldIndexed(false) { i, accum, value ->
        accum || (i + 1 until input.size).fold(false) { acc, index -> acc || value == 2 * input[index] || 2 * value == input[index] }
      }
}

fun main() {
  val checkIfDoubleExist = CheckIfDoubleExist()
  listOf(intArrayOf(7, 1, 14, 11) to true).forEachIndexed { index, (input, value) ->
    val output = checkIfDoubleExist.execute(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")

  }
}