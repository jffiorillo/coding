package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1301/
class Maximum1Consecutive {
  fun execute(input: IntArray): Int {
    var maxCon = 0
    var index = 0
    while (index < input.size) {
      if (input[index] == 1) {
        val nextIndex = nextNo1Index(input, index)
        maxCon = maxOf(maxCon, nextIndex - index)
        index = nextIndex
      } else index++
    }
    return maxCon
  }

  private fun nextNo1Index(input: IntArray, index: Int): Int {
    (index until input.size).forEach {
      if (input[it] != 1) return it
    }
    return input.size
  }
}

fun main() {
  runTests(listOf(intArrayOf(1, 1, 0, 1, 1, 1) to 3)) { (input, value) -> value to Maximum1Consecutive().execute(input) }
}