package io.math

import io.utils.runTests
import java.lang.StringBuilder

// https://leetcode.com/problems/excel-sheet-column-title/
class SheetColumnTitle {

  fun execute(input: Int): String {
    var current = input
    val result = StringBuilder()
    val aValue = 'A'
    while (current > 0) {
      result.append(aValue + (current - 1).rem(26))
      current = (current - 1) / 26
    }
    return result.reverse().toString()
  }

  fun convertToTitle(input: Int): String = if (input == 0) "" else convertToTitle((input - 1) / 26) + ('A' + (input - 1).rem(26))
}

fun main() {
  runTests(listOf(
      1428 to "BBX",
      26 to "Z",
      28 to "AB",
      18987 to "ABBG",
      52 to "AZ",
      1 to "A"
  )) { (input, value) -> value to SheetColumnTitle().convertToTitle(input) }
}