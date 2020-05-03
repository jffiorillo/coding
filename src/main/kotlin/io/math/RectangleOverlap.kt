package io.math

import io.utils.runTests

// https://leetcode.com/problems/rectangle-overlap/
class RectangleOverlap {

  fun execute(input0: IntArray, input1: IntArray): Boolean =
      input0[0] < input1[2] && input1[0] < input0[2] &&
          input0[1] < input1[3] && input1[1] < input0[3]

}

fun main() {

  runTests(listOf(
      Triple(intArrayOf(0, 0, 2, 2), intArrayOf(1, 1, 3, 3), true),
      Triple(intArrayOf(0, 0, 1, 1), intArrayOf(1, 0, 2, 1), false),
      Triple(intArrayOf(2, 17, 6, 20), intArrayOf(3, 8, 6, 20), true)
  )) { (input0, input1, value) -> value to RectangleOverlap().execute(input0, input1) }
}