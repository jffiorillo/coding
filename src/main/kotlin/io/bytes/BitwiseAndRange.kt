package io.bytes

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3308/
class BitwiseAndRange {

  fun execute(m: Int, n: Int): Int {
    var result: Int? = null
    for (item in m..n) {
      if (result == 0)
        break
      result = result?.let { it and item } ?: item
    }
    return result ?: 0
  }
}

fun main() {
  runTests(listOf(
      Triple(5, 7, 4),
      Triple(0, 0, 0),
      Triple(0, 1, 0),
      Triple(100, 2147483644, 0),
      Triple(Int.MAX_VALUE - 1, Int.MAX_VALUE, Int.MAX_VALUE - 1)
  )) { (m, n, value) -> value to BitwiseAndRange().execute(m, n) }
}