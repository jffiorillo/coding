package io.dp

import io.utils.runTests

const val MAX_INPUT_SIZE = 100

// https://leetcode.com/problems/remove-boxes/
class RemovingBoxes {

  fun execute(input: IntArray,
              dp: Array<Array<IntArray>> = Array(MAX_INPUT_SIZE) { Array(MAX_INPUT_SIZE) { IntArray(MAX_INPUT_SIZE) } },
              left: Int = 0,
              right: Int = input.size - 1,
              s: Int = 0): Int {
    var leftTrim = left
    var streak = s

    if (left > right) return 0
    if (dp[left][right][streak] > 0) {
      return dp[left][right][streak]
    }

    if (streak == 0) streak = 1
    while (leftTrim < right && input[leftTrim] == input[leftTrim + 1]) {
      leftTrim++
      streak++
    }

    var max = execute(input, dp, leftTrim + 1, right, 0) + streak * streak

    for (index in leftTrim..right) {
      if (input[index] == input[left]) {
        max = maxOf(max,
            execute(input, dp, leftTrim, index - 1, streak + 1) + execute(input, dp, index + 1, right, 0))
      }
    }
    dp[left][right][s] = max
    return dp[left][right][s]
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(1, 3, 2, 2, 2, 3, 4, 3, 1) to 23
  )) { (input, value) -> value to RemovingBoxes().execute(input) }
}