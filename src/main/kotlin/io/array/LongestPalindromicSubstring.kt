package io.array

import io.utils.runTests

class LongestPalindromicSubstring {

  fun executeManacher(input: String): String {
    if (input.isEmpty()) return ""
    val preprocessManacher = preprocessManacher(input)
    val weights = IntArray(preprocessManacher.length) { 0 }

    var center = 0
    var rightBoundary = 0
    (1 until preprocessManacher.length - 1).forEach { index ->
      val mirror = 2 * center - index
      if (index < rightBoundary)
        weights[index] = minOf(rightBoundary - index, weights[mirror])

      while (preprocessManacher[index + (1 + weights[index])] == preprocessManacher[index - (1 + weights[index])]) {
        weights[index]++
      }

      if (index + weights[index] > rightBoundary) {
        rightBoundary = index + weights[index]
        center = index
      }
    }

    return cleanSolution(preprocessManacher, weights)
  }

  private fun cleanSolution(input: String, weights: IntArray) =
      weights
          .foldIndexed(0 to 0) { index, (center, size), value ->
            if (value > size) {
              index to value
            } else center to size
          }.let { (center, size) -> input.substring(center - size..center + size).replace("#", "") }

  private fun preprocessManacher(input: String) = "^$input$".toList().joinToString(separator = "#") { it.toString() }

  fun execute(input: String): String = when (input.length) {
    0, 1 -> input
    else -> {

      val palindromes = (1 until input.length - 1).fold(mutableListOf<Pair<Int, Int>>()) { acc, value ->
        acc.apply {
          if (input.isPalindrome(value - 1, value + 1)) {
            add(value - 1 to value + 1)
          }
        }
      }
      palindromes.addAll((1 until input.length).fold(mutableListOf()) { acc, value ->
        acc.apply {
          if (input.isPalindrome(value - 1, value)) {
            add(value - 1 to value)
          }
        }
      })

      palindromes.fold(input.first().toString()) { acc, (f, l) ->
        var first = f
        var last = l
        while (first > 0 && last < input.length - 1 && input[first - 1] == input[last + 1]) {
          first--
          last++
        }
        var word = input.substring(first..last)
        if (word.isNotEmpty() && word.all { it == word.first() }) {
          while (first > 0 && input[first - 1] == word.first()) {
            first--
          }
          while (last < input.length - 1 && input[last + 1] == word.first()) {
            last++
          }
          word = input.substring(first..last)
        }
        if (word.length > acc.length) word else acc
      }
    }
  }


  fun String.isPalindrome(f: Int, l: Int): Boolean {
    if (f !in indices || l !in indices) return false
    var first = f
    var last = l
    while (first < length && last >= 0 && first < last && this[first] == this[last]) {
      first++
      last--
    }
    return first >= last
  }
}

fun main() {
  runTests(listOf(
      "cbbd" to "bb",
      "babad" to "bab",
      "a" to "a",
      "ac" to "a",
      "aaaa" to "aaaa",
      "tattarrattat" to "tattarrattat"
  )) { (input, value) -> value to LongestPalindromicSubstring().executeManacher(input) }
}