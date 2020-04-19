package io.queue

import io.utils.runTests

// https://leetcode.com/problems/roman-to-integer/
class RomanToInteger {

  fun execute(input: String): Int {
//    "MCMXCIV"
    var index = 0
    var result = 0
    while (index in input.indices) {
      val value = input[index].toNumber()
      if (index + 1 < input.length) {
        val nextValue = input[index + 1].toNumber()
        result += if (nextValue > value) {
          index++
          nextValue - value
        } else value
      }else {
        result += value
      }
      index++
    }
    return result
  }
}

private fun Char.toNumber() = when (this) {
  'I' -> 1
  'V' -> 5
  'X' -> 10
  'L' -> 50
  'C' -> 100
  'D' -> 500
  'M' -> 1000
  else -> 0
}

fun main() {
  runTests(listOf(
      "X" to 10,
      "IV" to 4,
      "III" to 3,
      "IX" to 9,
      "LVIII" to 58,
      "LXXX" to 80,
      "MCMXCIV" to 1994,
      "MCMLXXXIX" to 1989
  )) { (input, value) -> value to RomanToInteger().execute(input) }
}