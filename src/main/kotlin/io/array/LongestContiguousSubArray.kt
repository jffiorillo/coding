package io.array

import io.utils.runTests


// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3298/

class LongestContiguousSubArray {

  fun execute(input: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    // when 0 is found, we need make maxLen index - -1 == index + 1
    map[0] = -1
    var maxLen = 0
    var count = 0
    input.forEachIndexed { index, value ->
      count += if (value == 1) 1 else -1
      maxLen = maxOf(maxLen, index - map.getOrPut(count) { index })
    }
    return maxLen
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(0, 1, 0) to 2,
      intArrayOf(0, 1) to 2,
      intArrayOf(1, 0) to 2,
      intArrayOf(0, 0, 1, 0, 0, 0, 1, 1) to 6
  )) { (input, value) -> value to LongestContiguousSubArray().execute(input) }
}