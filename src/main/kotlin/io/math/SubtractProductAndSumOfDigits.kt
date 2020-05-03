package io.math

import io.utils.runTests

// https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
class SubtractProductAndSumOfDigits {

  fun execute(input: Int): Int {
    var current = input
    var product = 1
    var sum = 0
    while (current > 0) {
      val value = current.rem(10)
      product *= value
      sum += value
      current /= 10
    }
    return product - sum
  }
}

fun main() {
  runTests(listOf(234 to 15)) { (input, value) -> value to SubtractProductAndSumOfDigits().execute(input) }
}