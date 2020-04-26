package io.dp

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3311/
class LongestCommonSubsequence {

  fun execute(input0: String, input1: String): Int = when {
    input0.isEmpty() || input1.isEmpty() -> 0
    else -> {
      val memoization = Array(input0.length) { IntArray(input1.length) { 0 } }
      input0.indices.forEach { i0 ->
        input1.indices.forEach { i1 ->
          memoization[i0][i1] = if (input0[i0] == input1[i1]) {
            if (i0 - 1 >= 0 && i1 - 1 >= 0) memoization[i0 - 1][i1 - 1] + 1 else 1
          } else {
            maxOf(if (i0 - 1 >= 0) memoization[i0 - 1][i1] else 0, if (i1 - 1 >= 0) memoization[i0][i1 - 1] else 0)
          }
        }
      }
      memoization[input0.length - 1][input1.length - 1]
    }
  }
}

fun main() {
  runTests(listOf(
      Triple("yxabcdefghxy", "ragbg", 3),
      Triple("yxabcdefghxy", "", 0),
      Triple("abcde", "adcde", 4)
  )) { (input0, input1, value) -> value to LongestCommonSubsequence().execute(input0, input1) }
}