package io.dp

import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/longest-increasing-subsequence/
class LongestIncreasingSubsequence {

  fun execute(input: IntArray): Int {
    val dp = IntArray(input.size)
    var len = 0
    for (value in input) {
      val index = Arrays.binarySearch(dp, 0, len, value).let { index -> if (index < 0) -(index + 1) else index }
      dp[index] = value
      if (index == len) len++
    }
    return len
  }

  // https://www.youtube.com/watch?v=S9oUiVYEq7E&feature=emb_title
  fun execute1(input: IntArray): Int {
    if (input.isEmpty()) return 0
    val dp = IntArray(input.size)
    var len = 0
    input.forEachIndexed { index, value ->
      if (value > input[dp[len]]) {
        len++
        dp[len] = index
      } else {
        val dpIndex = dp.binarySearch(input, value, 0, len)
        dp[dpIndex] = index
      }
    }
    return len + 1
  }

}

private fun IntArray.binarySearch(input: IntArray, value: Int, start: Int, end: Int): Int {
  var first = start
  var last = end
  while (first <= last) {
    val pivot = first + (last - first) / 2
    when {
      input[this[pivot]] == value -> return pivot
      input[this[pivot]] < value -> first = pivot + 1
      else -> last = pivot - 1
    }
  }
  return first
}

fun main() {
  runTests(listOf(
      intArrayOf(10, 9, 2, 5, 3, 7, 101, 18) to 4,
      intArrayOf(5, 7, 9, 8, 4, 15, 3, 5, 14, 3, 6, 122, 1231, 124, 7, 3, 5, 6, 14, 8, -1, 12, 10, 9, -14, -18, 14, 31, 2, 2, 4, 10, 11, 12) to 9,
      intArrayOf(3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10) to 6
  )) { (input, value) -> value to LongestIncreasingSubsequence().execute1(input) }
}