package io.array

import io.utils.runTests

// https://leetcode.com/problems/maximum-subarray/
class MaxSumArray {
  fun execute(input: IntArray): Int =
      if (input.all { it <0 }) input.max()?:0 else
        input.fold(Int.MIN_VALUE to 0){acc, value -> maxOf((acc.second + value),0).let { sum  -> maxOf(acc.first, sum) to sum } }.first

  fun executeDP(nums: IntArray): Int {
    val dp = IntArray(nums.size){nums[it]}
    var max = dp[0]
    for (i in 1 until nums.size) {
      if (dp[i - 1] > 0) dp[i] = dp[i] + dp[i - 1]
      if (dp[i] > max) max = dp[i]
    }
    return max
  }
}

fun main(){

  runTests(listOf(
      intArrayOf(-2,1,-3,4,-1,2,1,-5,4) to 6,
      intArrayOf(-1) to -1
  )){ (input,value) -> value to MaxSumArray().execute(input) }
}