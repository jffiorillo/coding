package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
class ReduceNumberToZero {

  fun execute(input: Int): Int {
    var current = input
    var result = 0
    while (current != 0) {
      current = if (current.rem(2) == 0) current / 2 else current - 1
      result++
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      14 to 6,
      8 to 4,
      123 to 12
  )) { (input, value) -> value to ReduceNumberToZero().execute(input) }
}