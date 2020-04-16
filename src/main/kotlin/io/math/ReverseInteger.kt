package io.math

import io.utils.runTests
import kotlin.math.absoluteValue
import kotlin.math.pow

// https://leetcode.com/problems/reverse-integer/
class ReverseInteger {

  fun execute(input: Int): Int {
    var current = input
    var result = 0
    while (current != 0) {
      val pop = current.rem(10)
      current /= 10
      if (result > Int.MAX_VALUE / 10 || result == Int.MAX_VALUE / 10 && pop > 7) return 0
      if (result < Int.MIN_VALUE / 10 || result == Int.MIN_VALUE / 10 && pop < -8) return 0
      result = result * 10 + pop
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      123 to 321,
      0 to 0,
      -123 to -321,
      1534236469 to 0,
      -2147483648 to 0
  )) { (input, value) -> value to ReverseInteger().execute(input) }
}