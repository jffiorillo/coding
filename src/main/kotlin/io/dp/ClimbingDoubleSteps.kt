package io.dp

import io.utils.runTests

// https://leetcode.com/problems/climbing-stairs/
class ClimbingDoubleSteps {

  fun execute(stairs: Int): Int = when (stairs) {
    0 -> 0
    else -> {
      var n2 = 1
      var n1 = 1
      var currentMax = 1
      repeat(stairs - 1) {
        val currentVal = n2 + n1
        n2 = n1
        n1 = currentVal
        currentMax = currentVal
      }
      currentMax
    }
  }
}

fun main() {
  runTests(listOf(
      1 to 1,
      2 to 2,
      3 to 3,
      5 to 8,
      6 to 13
  )) { (input, value) -> value to ClimbingDoubleSteps().execute(input) }
}