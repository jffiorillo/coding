package io.bytes

import io.utils.runTests
import kotlin.math.absoluteValue
import kotlin.math.pow

// https://leetcode.com/problems/number-of-1-bits/
class NumberOfBits1HammingWeight {

  fun execute(input: Int): Int {
    var current = input
    var result = 0
    while (current != 0) {
      result += current and 1
      current = current ushr 1
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      1 to 1,
      5 to 2,
      7 to 3,
      15 to 4,
      Int.MIN_VALUE to 1,
      2.toDouble().pow(31).toInt() to 31
  ) + (1..30).map { 2.toDouble().pow(it).toInt() to 1 }
  ) { (input, value) -> value to NumberOfBits1HammingWeight().execute(input) }
}


