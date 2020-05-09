package io.array

import io.utils.runTests

// https://leetcode.com/problems/minimum-window-substring/
class MinimumWindowSubstring {
  fun execute(input: String, chars: String): String {
    if (chars.isEmpty() || input.isEmpty() || chars.length > input.length) return ""
    val charsSet = chars.fold(mutableMapOf<Char, Int>()) { acc, value -> acc.apply { this[value] = this.getOrDefault(value, 0) + 1 } }
    val info = input.fold(mutableMapOf<Char, Int>()) { acc, value ->
      acc.apply {
        if (charsSet.containsKey(value)) this[value] = this.getOrDefault(value, 0) + 1
      }
    }
    if (!info.keys.containsAll(charsSet.keys) || charsSet.any { (key, value) -> value > info.getValue(key) }) return ""

    var start = 0
    var end = 1
    val content = mutableMapOf(input[start] to mutableSetOf(start))
    var result = input
    while (end <= input.length && start <= (input.length - chars.length)) {
      if (content.keys.containsAll(charsSet.keys) && charsSet.all { (key, value) -> content.getValue(key).size >= value }) {
        if (result.length > end - start) result = input.substring(start until end)
        val key = input[start]
        val listOfIndices = content.getValue(key)
        listOfIndices.remove(start)
        if (listOfIndices.isEmpty()) content.remove(key)
        start++
        while (start < input.length && !charsSet.contains(input[start])) {
          start++
        }
      } else {
        if (end < input.length) {
          val key = input[end]
          if (charsSet.contains(key)) content.getOrPut(key) { mutableSetOf() }.add(end)
        }
        end++
      }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      Triple("ADOBECODEBANC", "ABC", "BANC"),
      Triple("a", "aa", ""),
      Triple("ab", "b", "b"),
      Triple("aa", "aa", "aa"),
      Triple("babb", "baba", "")
  )) { (input, chars, value) -> value to MinimumWindowSubstring().execute(input, chars) }
}