package io.matrix

import io.utils.runTests

// https://leetcode.com/problems/range-sum-query-2d-immutable/
class RangeSumQuery2DMatrix(private val matrix: Array<IntArray>) {

  private val dp = Array(matrix.size) { IntArray(matrix.firstOrNull()?.size ?: 0) }.apply {
    if (matrix.isNotEmpty() && matrix.first().isNotEmpty()) {
      for (row in matrix.indices) {
        var accum = 0
        for (col in matrix.first().indices) {
          val up = if (row == 0) 0 else this[row - 1][col]
          accum += matrix[row][col]
          this[row][col] = accum + up
        }
      }
    }
  }

  // Time O(1) Space O(NM)
  fun executeConstant(topRow: Int, topCol: Int, bottomRow: Int, bottomCol: Int): Int = when {
    topRow > 0 && topCol > 0 -> {
      val up = dp[topRow - 1][bottomCol]
      val left = dp[bottomRow][topCol - 1]
      val sum = dp[topRow - 1][topCol - 1]
      dp[bottomRow][bottomCol] - up - left + sum
    }
    topRow > 0 -> {
      val up = dp[topRow - 1][bottomCol]
      dp[bottomRow][bottomCol] - up
    }
    topCol > 0 -> {
      val left = dp[bottomRow][topCol - 1]
      dp[bottomRow][bottomCol] - left
    }
    else -> dp[bottomRow][bottomCol]
  }

  // Time O(NM) Space O(1)
  fun execute(topRow: Int, topCol: Int, bottomRow: Int, bottomCol: Int): Int {
    var result = 0
    for (row in topRow..bottomRow) {
      if (row in matrix.indices) {
        for (col in topCol..bottomCol) {
          if (col in 0 until (matrix.firstOrNull()?.size ?: 0))
            result += matrix[row][col]
        }
      }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      Triple(arrayOf(intArrayOf(3, 0, 1, 4, 2), intArrayOf(5, 6, 3, 2, 1), intArrayOf(1, 2, 0, 1, 5), intArrayOf(4, 1, 0, 1, 7), intArrayOf(1, 0, 3, 0, 5)),
          listOf(2, 1, 4, 3), 8)
  )) { (input, points, value) -> value to RangeSumQuery2DMatrix(input).executeConstant(points.first(), points[1], points[2], points[3]) }
}