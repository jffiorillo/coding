package io.math

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/string-to-integer-atoi/
class StringToIntAtoi {

  fun execute(input: String): Int {
    val firstNoWhiteSpace = input.firstNoWhiteSpace()
    if (firstNoWhiteSpace !in input.indices) return 0
    val firstChar = input[firstNoWhiteSpace]
    if (!firstChar.isFirstCharValid() || (!firstChar.isDigit() && firstNoWhiteSpace + 1 == input.length)) return 0
    var result = 0
    val sign = if (firstChar.isPositive() || firstChar.isDigit()) 1 else -1
    val firstNumber = if (firstChar.isDigit()) firstNoWhiteSpace else firstNoWhiteSpace + 1
    loop@ for (index in firstNumber until input.length) {
      val char = input[index]
      val value = (char - '0')
      when {
        !char.isDigit() -> break@loop
        sign.isPositive() && (result > Int.MAX_VALUE / 10 || result == Int.MAX_VALUE / 10 && value > Int.MAX_VALUE.rem(10)) -> {
          return Int.MAX_VALUE
        }
        sign.isNegative() && (result > Int.MAX_VALUE / 10 || result == Int.MAX_VALUE / 10 && value > Int.MIN_VALUE.rem(10).absoluteValue) -> {
          return Int.MIN_VALUE
        }
        else -> result = 10 * result + value
      }
    }
    return sign * result
  }
}

private fun Int.isNegative() = this < 0
private fun Int.isPositive() = !isNegative()

private fun Char.isFirstCharValid() = when (this) {
  '-', '+' -> true
  else -> (this - '0') in 0..9
}

private fun Char.isPositive() = this == '+'

private fun String.firstNoWhiteSpace(): Int =
    this.mapIndexed { index, char -> index to char }.firstOrNull { it.second != ' ' }?.first ?: this.length

fun main() {
  runTests(listOf(
      "42" to 42,
      "   -42" to -42,
      "4193 with words" to 4193,
      "words and 987" to 0,
      "-91283472332" to Int.MIN_VALUE,
      "-2147483648" to Int.MIN_VALUE,
      " -1010023630o4" to -1010023630
  )) { (input, value) -> value to StringToIntAtoi().execute(input) }
}
