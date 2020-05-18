package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/strobogrammatic-number/
class StrobogrammaticNumber {

  fun execute1(input: String): Boolean =
      input.fold(StringBuilder()) { acc, value ->
        acc.apply {
          when (value) {
            '1' -> append("1")
            '0' -> append("0")
            '6' -> append("9")
            '8' -> append("8")
            '9' -> append("6")
            else -> return false
          }
        }
      }.toString().let { rotated ->

        input.forEachIndexed { index, value ->
          if (value != rotated[input.lastIndex - index]) return false
        }
        return true
      }

  // https://leetcode.com/problems/strobogrammatic-number/discuss/67188/4-lines-in-Java
  fun execute(input: String): Boolean {
    var start = 0
    var end = input.lastIndex
    while (start <= end) {
      if (!"00 11 88 696".contains("${input[start]}${input[end]}")) return false
      start++
      end--
    }
    return true
  }
}

fun main() {
  runTests(listOf(
      "69" to true,
      "96" to true,
      "888" to true,
      "12" to false,
      "693" to false
  )) { (input, value) -> value to StrobogrammaticNumber().execute(input) }
}