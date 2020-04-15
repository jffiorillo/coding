package io.array

import io.utils.runTests

class LongestPalindromicSubstring {

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
          if (input.isPalindrome(value-1,value)) {
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
  )) { (input, value) -> value to LongestPalindromicSubstring().execute(input) }
}