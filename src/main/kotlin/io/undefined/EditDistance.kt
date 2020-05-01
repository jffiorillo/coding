package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/edit-distance/
class EditDistance {

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
    initial.forEachIndexed { row, rowValue ->
      goal.forEachIndexed { col, value ->
        dp[row + 1][col + 1] = if (rowValue == value) dp[row][col] else minOf(dp[row][col + 1], dp[row + 1][col], dp[row][col]) + 1
      }
    }
    return dp[initial.length][goal.length]
  }
}


fun main() {
  runTests(listOf(
//      Triple("horse", "ros", 3),
      Triple("zoologicoarchaeologist", "zoogeologist", 10)
  )) { (initial, goal, value) -> value to EditDistance().execute(initial, goal) }
}