package io.dp

import io.utils.runTests


// https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
class MinimumAsciiDeleteTwoStrings {

  fun execute(initial: String, goal: String): Int {
    val dp = Array(initial.length + 1) { row ->
      IntArray(goal.length + 1) { col ->
        when {
          row == 0 && col != 0 -> (0 until col).fold(0) { acc, index -> acc + goal[index].toInt() }
          row != 0 && col == 0 -> (0 until row).fold(0) { acc, index -> acc + initial[index].toInt() }
          else -> 0
        }
      }
    }

    initial.forEachIndexed { row, initialValue ->
      goal.forEachIndexed { col, goalValue ->
        dp[row + 1][col + 1] =
            if (initialValue == goalValue) dp[row][col]
            else minOf(dp[row][col] + initialValue.toInt() + goalValue.toInt(), dp[row + 1][col] + goalValue.toInt(), dp[row][col + 1] + initialValue.toInt())
      }
    }
    return dp[initial.length][goal.length]
  }
}

fun main() {
  runTests(listOf(
      Triple("sea", "eat", 231),
      Triple("delete", "leet", 403)
  )) { (initial, goal, value) -> value to MinimumAsciiDeleteTwoStrings().execute(initial, goal) }
}