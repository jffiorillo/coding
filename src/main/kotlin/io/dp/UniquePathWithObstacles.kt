package io.dp

import io.utils.runTests

// https://leetcode.com/problems/unique-paths-ii/
class UniquePathWithObstacles {

  fun execute(input: Array<IntArray>): Int {
    if (input.isEmpty() || input.first().isEmpty())
      return 0
    val row = input.size
    val col = input.first().size
    val dp = createDpArray(input)
    initializeFirstColumn(col, dp)
    initializeFirstRow(row, dp)

    (1 until row).forEach { r ->
      (1 until col).forEach { c ->
        if (dp[r][c] != -1) {
          val top = dp[r - 1][c].let { if (it > 0) it else 0 }
          val left = dp[r][c - 1].let { if (it > 0) it else 0 }
          dp[r][c] = top + left
        }
      }
    }
    return dp.last().last().let { if (it < 0) 0 else it }
  }

  private fun createDpArray(input: Array<IntArray>): Array<IntArray> {
    return Array(input.size) { r ->
      IntArray(input.first().size) { c ->
        when {
          input[r][c] == 1 -> -1
          else -> 0
        }
      }
    }
  }

  private fun initializeFirstColumn(col: Int, dp: Array<IntArray>) {
    (0 until col).forEach { c ->
      if (dp.first()[c] != -1 && (c == 0 || dp.first()[c - 1] > 0)) {
        dp.first()[c] = 1
      }
    }
  }

  private fun initializeFirstRow(row: Int, dp: Array<IntArray>) {
    (0 until row).forEach { r ->
      if (dp[r].first() != -1 && (r == 0 || dp[r - 1].first() > 0)) {
        dp[r][0] = 1
      }
    }
  }

}

fun main() {

  runTests(listOf(
//      arrayOf(
//          intArrayOf(0, 1, 0, 0, 0),
//          intArrayOf(0, 0, 0, 1, 0),
//          intArrayOf(0, 0, 0, 0, 0)
//      ) to 3,
//      arrayOf(
//          intArrayOf(0, 0, 0),
//          intArrayOf(0, 1, 0),
//          intArrayOf(0, 0, 0)
//      ) to 2,
      arrayOf(
          intArrayOf(1, 0)
      ) to 0
  )) { (input, value) -> value to UniquePathWithObstacles().execute(input) }
}