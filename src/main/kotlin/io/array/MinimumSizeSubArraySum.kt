package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1299/
class MinimumSizeSubArraySum {

  fun execute(target: Int, input: IntArray): Int {
    var result = Int.MAX_VALUE
    var left = 0
    var sum = 0
    for (index in input.indices) {
      sum += input[index]
      while (sum >= target) {
        result = minOf(result, index + 1 - left)
        sum -= input[left++]
      }
    }
    return when (result) {
      Int.MAX_VALUE -> 0
      else -> result
    }
  }

  fun executeBruteForce(target: Int, input: IntArray): Int {
    for (minSize in 1..input.size) {
      for (startIndex in input.indices) {
        (startIndex until (startIndex + minSize)).fold(0) { acc, index ->
          when {
            index >= input.size -> acc
            acc + input[index] >= target -> return minSize
            else -> acc + input[index]
          }
        }
      }
    }
    return 0
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(2, 3, 1, 2, 4, 3), 7, 2),
      Triple(intArrayOf(2, 3, 1, 2, 4, 3), 50, 0),
      Triple(intArrayOf(1, 2, 3, 4, 5), 11, 3),
      Triple(intArrayOf(1, 2, 3, 4, 5), 15, 5)
  )) { (input, target, value) -> value to MinimumSizeSubArraySum().execute(target, input) }
}
