package io.string

import io.utils.runTests

// https://leetcode.com/problems/first-unique-character-in-a-string/
class FirstUniqueCharacter {

  fun execute(input: String): Int {
    val map = IntArray(26)
    input.forEach { char -> map[char - 'a']++ }
    input.forEachIndexed { index, char -> if (map[char - 'a'] == 1) return index }
    return -1
  }

  fun execute1(input: String): Int {
    val map = mutableMapOf<Char, Pair<Int, Int>>()

    input.forEachIndexed { index, char ->
      map[char] = map[char]?.let { (count, first) -> count + 1 to first } ?: 1 to index
    }
    return map.values.fold(null as Int?) { acc, (count, index) -> if (count == 1 && (acc == null || acc > index)) index else acc }
        ?: -1
  }
}

fun main() {
  runTests(listOf(
      "leetcode" to 0,
      "loveleetcode" to 2,
      "abcabc" to -1
  )) { (input, value) -> value to FirstUniqueCharacter().execute(input) }
}