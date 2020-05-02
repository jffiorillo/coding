package io.array

import io.utils.runTests

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
class LongestSubstringWithoutRepeatingChars {

  // https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235235/C%2B%2BJava-with-picture-prefixed-sliding-window
  fun execute(input: String): Int {
    var current = 0
    var first = 0
    var max = 0
    val map = mutableMapOf<Char, Int>()
    while (current < input.length) {
      val value = input[current]
      if (map.containsKey(value)) {
        first = maxOf(map.getValue(value) + 1, first)
      }
      map[value] = current
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

