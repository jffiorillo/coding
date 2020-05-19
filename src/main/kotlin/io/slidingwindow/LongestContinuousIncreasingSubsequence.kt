@file:Suppress("unused")

package io.slidingwindow

// https://leetcode.com/problems/longest-continuous-increasing-subsequence/
class LongestContinuousIncreasingSubsequence {

  fun executeSlidingWindow(input: IntArray): Int {
    if (input.isEmpty()) return 0
    var result = 1
    var index = 1
    var start = 0
    while (index < input.size) {
      if (input[index - 1] >= input[index]) start = index
      result = maxOf(result, index - start + 1)
      index++
    }
    return result
  }

  fun execute(input: IntArray): Int {
    if (input.isEmpty()) return 0
    var result = 1
    var index = 1
    while (index < input.size) {
      var accum = 1
      while (index < input.size && input[index - 1] < input[index]) {
        accum++
        index++
      }
      result = maxOf(result, accum)
      index++
    }
    return result
  }
}