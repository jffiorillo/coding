package io.array

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3307/
class SubarraySumEqualsK {

  fun execute(input: IntArray, target: Int): Int {
    var result = 0
    val stack = input.map { elem -> elem.also { if (elem == target) result++ } }.toMutableList()
    for (offset in 1 until input.size) {
      var index = 0
      while (index + offset < input.size) {
        stack[index] += input[index + offset]
        result += if (stack[index] == target) 1 else 0
        index++
      }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(1, 1, 2, 1, 2, 1, 1, 1, 1), 2, 6),
      Triple(intArrayOf(1, 1, 1), 2, 2),
      Triple(intArrayOf(-1, -1, 1), 0, 1),
      Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7, 1, 23, 21, 3, 1, 2, 1, 1, 1, 1, 1, 12, 2, 3, 2, 3, 2, 2), 6, 5),
      Triple(intArrayOf(-1, -1, 1), 1, 1),
      Triple(intArrayOf(-92, -63, 75, -86, -58, 22, 31, -16, -66, -67, 420), 100, 1)
  )) { (input, target, value) -> value to SubarraySumEqualsK().execute(input, target) }
}

