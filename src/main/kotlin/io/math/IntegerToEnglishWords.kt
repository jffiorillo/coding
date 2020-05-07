package io.math

import io.utils.runTests
import java.lang.StringBuilder


private val LESS_THAN_20 = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
    "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
private val TENS = arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
private val THOUSANDS = arrayOf("", "Thousand", "Million", "Billion")

// https://leetcode.com/problems/integer-to-english-words/
class IntegerToEnglishWords {

  fun execute(number: Int): String {
    if (number == 0) return "Zero"
    var current = 0
    var input = number
    val result = StringBuilder()
    while (input > 0) {
      val mod = input % 1000
      if (mod != 0) result.insert(0, "${helper(mod)} ${THOUSANDS[current]} ")
      input /= 1000
      current++
    }
    return result.toString().trim()
  }

  private fun helper(input: Int): String = when {
    input == 0 -> ""
    input < 20 -> LESS_THAN_20[input]
    input < 100 -> "${TENS[input / 10]} ${helper(input % 10)}".trim()
    else -> "${LESS_THAN_20[input / 100]} Hundred ${helper(input % 100)}".trim()
  }

}

fun main() {
  runTests(listOf(
      123 to "One Hundred Twenty Three",
      12345 to "Twelve Thousand Three Hundred Forty Five",
      1234567 to "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
      1234567891 to "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One",
      50868 to "Fifty Thousand Eight Hundred Sixty Eight"
  )) { (input, value) -> value to IntegerToEnglishWords().execute(input) }

}