package io.undefined

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/maximum-of-absolute-value-expression/
class MaxOfAbsoluteValueExpression {

  fun execute(input0: IntArray, input1: IntArray): Int {
    var max = 0
    input0.indices.forEach { index ->
      (index + 1 until input1.size).forEach { j ->
        max = maxOf(max, (input0[index] - input0[j]).absoluteValue + (input1[index] - input1[j]).absoluteValue + (index - j).absoluteValue)
      }
    }
    return max
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(1, 2, 3, 4), intArrayOf(-1, 4, 5, 6), 13),
      Triple(intArrayOf(1, -2, -5, 0, 10), intArrayOf(0, -2, -1, -7, -4), 20)
  )) { (input0, input1, value) -> value to MaxOfAbsoluteValueExpression().execute(input0, input1) }
}