package io.array

import io.utils.runTests

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
class LongestSubstringWithoutRepeatingChars {

  fun execute(input: String): Int {
    var first = 0
    var current = 0
    var max = 0
    var map = mutableMapOf<Char, Int>()
    while (current < input.length) {
      if (map.containsKey(input[current])) {
        first = map.getValue(input[current]) + 1
        map = (first until current).map { input[it] to it }.toMap().toMutableMap()
      }
      map[input[current]] = current
      max = maxOf(max, current - first + 1)
      current++
    }

    return max
  }
}

fun main() {
  runTests(listOf(
      "abcabccbb" to 3,
      "bbbbb" to 1,
      "pwwkew" to 3,
      "abba" to 2,
      "abbbbbbbbbbbbbbbbbbbcdeafa" to 6
  )) { (input, value) -> value to LongestSubstringWithoutRepeatingChars().execute(input) }
}

