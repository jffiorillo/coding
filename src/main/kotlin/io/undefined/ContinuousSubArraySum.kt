package io.undefined

import io.utils.runTests


// https://leetcode.com/problems/continuous-subarray-sum/
class ContinuousSubArraySum {

  fun execute(input: IntArray, multipleOf: Int): Boolean {
    val memory = mutableMapOf(0 to -1)
    var accum = 0
    input.forEachIndexed { index, item ->
      accum += item
      if (multipleOf != 0) accum %= multipleOf
      if (memory[accum]?.let { index - it > 1 } == true) return true
      memory.getOrPut(accum) { index }
    }
    return false
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(23, 2, 4, 6, 7), 6, true),
      Triple(intArrayOf(23, 2, 6, 4, 7), 6, true),
      Triple(intArrayOf(1, 2, 12), 6, false),
      Triple(intArrayOf(1, 0, 1, 0, 1), 4, false),
      Triple(intArrayOf(0, 0), -1, true),
      Triple(intArrayOf(1, 1), 2, true)
  )) { (input, k, value) -> value to ContinuousSubArraySum().execute(input, k) }
}