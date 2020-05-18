package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/goat-latin/
class GoatLatin {

  fun execute(input: String): String = input.split(" ").foldIndexed(StringBuilder()) { index, acc, value ->
    acc.apply {
      if (value.isNotEmpty()) {
        if (acc.isNotEmpty()) acc.append(" ")
        when (value.first().toLowerCase()) {
          'a', 'e', 'i', 'o', 'u' -> acc.append(value)
          else -> acc.append(value.substring(1)).append(value.first())
        }
        acc.append("ma")
        repeat(index + 1) { acc.append('a') }
      }
    }
  }.toString()
}

fun main() {
  runTests(listOf(
      "I speak Goat Latin" to "Imaa peaksmaaa oatGmaaaa atinLmaaaaa",
      "" to ""
  )) { (input, value) -> value to GoatLatin().execute(input) }
}