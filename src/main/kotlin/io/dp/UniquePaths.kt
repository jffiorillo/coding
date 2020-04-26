package io.dp

import io.utils.runTests

// https://leetcode.com/problems/unique-paths/
class UniquePaths {
  fun execute(row: Int, col: Int): Int {
    if (row <= 0 || col <= 0)
      return 0
    val dp = Array(row) { r -> IntArray(col) { if (r == 0 || it == 0) 1 else 0 } }

    (1 until row).forEach { r ->
      (1 until col).forEach { c ->
        dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
      }
    }
    return dp[row - 1][col - 1]
  }
}

fun main() {
  runTests(listOf(
      Triple(3, 4, 10),
      Triple(3, 2, 3)
  )) { (row, col, value) -> value to UniquePaths().execute(row, col) }
}