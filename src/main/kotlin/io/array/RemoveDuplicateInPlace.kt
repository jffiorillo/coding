package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1173/
class RemoveDuplicateInPlace {

  fun execute(input: IntArray): List<Int> =
      removeDuplicates(input).let { size -> input.toList().subList(0, size) }

  private fun removeDuplicates(input: IntArray): Int {
    if (input.isEmpty()) return 0
    var newIndex = 1
    (1 until input.size).map { input[it] }.forEach {
      if (input[newIndex-1] != it) {
        input[newIndex] = it
        newIndex++
      }
    }
    return newIndex
  }


}

fun main() {
  runTests(listOf(
      intArrayOf(1, 1, 2) to listOf(1, 2),
      intArrayOf(0,0,1,1,1,2,2,3,3,4) to listOf(0,1,2,3,4)
  )) { (input, value) -> value to RemoveDuplicateInPlace().execute(input) }
}