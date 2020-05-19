package io.permutation

// https://leetcode.com/problems/palindrome-permutation/
class PalindromePermutation {

  fun execute(input: String): Boolean {
    val information = mutableMapOf<Char, Int>()
    input.forEach { char -> information[char] = information.getOrDefault(char,0) + 1 }
    var foundOdd = false
    information.forEach { (_, value) ->
      when {
        value.rem(2) == 1 && !foundOdd -> foundOdd = true
        value.rem(2) == 1 -> return false
      }
    }
    return true
  }
}