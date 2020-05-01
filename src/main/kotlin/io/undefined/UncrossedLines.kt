package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/uncrossed-lines/
class UncrossedLines {


  fun execute1(input0: IntArray, input1: IntArray): Int =
      Array(input0.size+1) { IntArray(input1.size+1) }.let { dp ->
        for (i in 1..input0.size)
          for (j in 1..input1.size)
            if (input0[i-1] == input1[j-1]) dp[i][j] = 1 + dp[i - 1][j - 1]
            else dp[i][j] = maxOf(dp[i][j - 1], dp[i - 1][j])
        dp[input0.size][input1.size]
      }

  fun execute(input0: IntArray, input1: IntArray): Int = maxLines(input0, input1)

  private fun maxLines(
      input0: IntArray,
      input1: IntArray,
      index0: Int = 0,
      index1: Int = 0,
      dp: Array<IntArray> = Array(input0.size) { IntArray(input1.size) { -1 } }
  ): Int = when {
    index0 == input0.size || index1 == input1.size -> 0
    dp[index0][index1] >= 0 -> dp[index0][index1]
    else -> {
      val max = if (input0[index0] == input1[index1]) maxLines(input0, input1, index0 + 1, index1 + 1, dp) + 1 else 0
      maxOf(max, maxLines(input0, input1, index0 + 1, index1, dp), maxLines(input0, input1, index0, index1 + 1, dp)).also { dp[index0][index1] = it }
    }
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(1, 4, 2), intArrayOf(1, 2, 4), 2),
      Triple(intArrayOf(2, 5, 1, 2, 5), intArrayOf(10, 5, 2, 1, 5, 2), 3),
      Triple(intArrayOf(15, 14, 1, 7, 15, 1, 12, 18, 9, 15, 1, 20, 18, 15, 16, 18, 11, 8, 11, 18, 11, 11, 17, 20, 16, 20, 15, 15, 9, 18, 16, 4, 16, 1, 13, 10, 10, 20, 4, 18, 17, 3, 8, 1, 8, 19, 14, 10, 10, 12), intArrayOf(12, 8, 17, 4, 2, 18, 16, 10, 11, 12, 7, 1, 8, 16, 4, 14, 12, 18, 18, 19, 19, 1, 11, 18, 1, 6, 12, 17, 6, 19, 10, 5, 11, 16, 6, 17, 12, 1, 9, 3, 19, 2, 18, 18, 2, 4, 11, 11, 14, 9, 20, 19, 2, 20, 9, 15, 8, 7, 8, 6, 19, 12, 4, 11, 18, 18, 1, 6, 9, 17, 13, 19, 5, 4, 14, 9, 11, 15, 2, 5, 4, 1, 10, 11, 6, 4, 9, 7, 11, 7, 3, 8, 11, 12, 4, 19, 12, 17, 14, 18), 23)
  )) { (i0, i1, value) -> value to UncrossedLines().execute(i0, i1) }
}