package io.string

import io.utils.runTests

// https://leetcode.com/problems/valid-palindrome-ii/
class ValidPalindromeII {

  fun execute(input: String,
              start: Int = 0,
              end: Int = input.lastIndex,
              hasRemoved: Boolean = false): Boolean = when {
    start >= end -> true
    input[start] != input[end] && hasRemoved -> false
    input[start] != input[end] -> execute(input, start + 1, end, true) || execute(input, start, end - 1, true)
    else -> execute(input, start + 1, end - 1, hasRemoved)
  }
}

fun main() {
  runTests(listOf(
      "aba" to true,
      "abca" to true
  )) { (input, value) -> value to ValidPalindromeII().execute(input) }
}