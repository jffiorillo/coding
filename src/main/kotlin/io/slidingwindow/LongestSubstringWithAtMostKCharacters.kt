package io.slidingwindow

import io.utils.runTests

// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
class LongestSubstringWithAtMostKCharacters {
  fun execute(input: String, k: Int): Int {
    if (k == 0) return 0
    var result = 0
    var start = 0
    var end = 0
    val counts = mutableMapOf<Char, Int>()
    while (end < input.length) {
      counts[input[end]] = end
      when {
        counts.keys.size > k -> {
          val (key, value) = counts.entries.minByOrNull { (_, value) -> value }!!
          counts.remove(key)
          start = value + 1
        }
        else -> result = maxOf(result, end - start + 1)
      }
      end++
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      Triple("eceba", 2, 3),
      Triple("aa", 1, 2),
      Triple("a", 2, 1),
      Triple("bacc", 2, 3)

  )) { (input, k, value) -> value to LongestSubstringWithAtMostKCharacters().execute(input, k) }
}