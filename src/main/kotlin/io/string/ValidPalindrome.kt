package io.string

import io.utils.runTests

// https://leetcode.com/problems/valid-palindrome/
class ValidPalindrome {

  fun execute(input: String): Boolean {
    val cleanInput = input.lowercase().replace("[^a-z0-9]".toRegex(), "")
    var start = 0
    var end = cleanInput.lastIndex
    while (start < end) {
      if (cleanInput[start] != cleanInput[end]) {
        return false
      }
      start++
      end--
    }
    return true
  }
}

fun main() {
  runTests(listOf(
      "A man, a plan, a canal: Panama" to true
//      "race a car" to false
  )) { (input, value) -> value to ValidPalindrome().execute(input) }
}