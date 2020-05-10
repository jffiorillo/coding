package io.undefined

import io.utils.runTests


// https://leetcode.com/problems/find-all-anagrams-in-a-string/
class FindAllAnagramsInAString {

  fun execute(input: String, anagram: String): List<Int> {
    if (input.isEmpty() || anagram.length > input.length) return emptyList()
    val result = mutableListOf<Int>()
    val current = mutableMapOf<Char, Int>()
    val anagramMap = anagram.fold(mutableMapOf<Char, Int>()) { acc, value -> acc.apply { acc[value] = acc.getOrDefault(value, 0) + 1 } }
    for (index in anagram.indices) {
      val key = input[index]
      current[key] = current.getOrDefault(key, 0) + 1
    }
    for (index in anagram.length..input.length) {
      if (current == anagramMap) result.add(index - anagram.length)
      val startWord = input[index - anagram.length]
      when {
        current.getOrDefault(startWord, 0) == 1 -> current.remove(startWord)
        current.containsKey(startWord) -> current[startWord] = current.getValue(startWord) - 1
      }
      if (index < input.length) {
        val element = input[index]
        if (anagramMap.keys.contains(element)) current[element] = current.getOrDefault(element, 0) + 1
      }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      Triple("cbaebabacd", "abc", listOf(0, 6)),
      Triple("abab", "ba", listOf(0, 1, 2))
  )) { (input, anagram, value) -> value to FindAllAnagramsInAString().execute(input, anagram) }
}