package io.math

import io.utils.runTests

// https://leetcode.com/problems/missing-number/
class MissingNumber {

  fun execute(input: IntArray): Int =
      input.foldIndexed(0 to 0) { index, acc, value -> acc.first + index + 1 to acc.second + value }.let { it.first - it.second }

  fun execute1(input: IntArray): Int =
      input.indices.reduce{acc, index -> acc + index +1} + 1  - input.sum()
}

fun main() {
  runTests(listOf(
      intArrayOf(3, 0, 1) to 2,
      intArrayOf(9,6,4,2,3,5,7,0,1) to 8
  )) { (input, value) -> value to MissingNumber().execute(input) }
}