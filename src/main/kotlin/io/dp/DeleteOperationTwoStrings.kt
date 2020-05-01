package io.dp

import io.utils.runTests

// https://leetcode.com/problems/delete-operation-for-two-strings/
class DeleteOperationTwoStrings {

  fun execute(initial: String, goal: String): Int {
    val dp = Array(initial.length + 1) { row ->
      IntArray(goal.length + 1) { col ->
        when {
          row == 0 -> col
          col == 0 -> row
          else -> 0
        }
      }
    }

    initial.forEachIndexed { row, initialChar ->
      goal.forEachIndexed { col, goalChar ->
        dp[row + 1][col + 1] = if (goalChar == initialChar)
          dp[row][col]
        else
          minOf(dp[row][col] + 2, dp[row][col + 1] + 1, dp[row + 1][col] + 1)
      }
    }
    return dp[initial.length][goal.length]
  }
}

fun main() {
  runTests(listOf(
      Triple("sea", "eat", 2),
      Triple("spartan", "part", 3),
      Triple("food", "money", 7),
      Triple("plasma", "altruism", 8)
  )) { (initial, goal, value) -> value to DeleteOperationTwoStrings().execute(initial, goal) }
}