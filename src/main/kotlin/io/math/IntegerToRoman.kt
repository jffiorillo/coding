package io.math

import io.utils.runTests

// https://leetcode.com/problems/integer-to-roman/
class IntegerToRoman {
  fun execute(input: Int): String = input.toRomanNumber()
}


private fun Int.toRomanNumber(): String = when (this) {
  in Int.MIN_VALUE..0 -> ""
  in 1..3 -> "I".repeat(this)
  4 -> "IV"
  in 5..8 -> "V" + (this - 5).toRomanNumber()
  9 -> "IX"
  in 10 until 40 -> "X".repeat(this / 10) + (this.rem(10)).toRomanNumber()
  in 40 until 50 -> "XL" + (this - 40).toRomanNumber()
  in 50 until 90 -> "L" + (this - 50).toRomanNumber()
  in 90 until 100 -> "XC" + (this - 90).toRomanNumber()
  in 100 until 400 -> "C".repeat(this / 100) + (this.rem(100)).toRomanNumber()
  in 400 until 500 -> "CD" + (this - 400).toRomanNumber()
  in 500 until 900 -> "D" + (this - 500).toRomanNumber()
  in 900 until 1000 -> "CM" + (this - 900).toRomanNumber()
  else -> "M" + (this - 1000).toRomanNumber()
}

fun main(){
  runTests(listOf(
      4 to "IV",
      3 to "III",
      9 to "IX",
      58 to "LVIII",
      80 to "LXXX",
      1994 to "MCMXCIV",
      1989 to "MCMLXXXIX"
  )){(input,value) -> value to IntegerToRoman().execute(input)}
}
