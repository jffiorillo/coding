package io.graph

import io.utils.runTests

// https://leetcode.com/problems/word-break-ii/
class WordBreakII {

  fun execute(input: String, words: List<String>, start: Int = 0, dp: MutableMap<Int, List<String>> = mutableMapOf()): List<String> {
    if (dp.containsKey(start)) return dp.getValue(start)
    val builder = StringBuilder()
    val result = mutableSetOf<String>()
    for (index in start until input.length) {
      builder.append(input[index])
      val word = builder.toString()
      when {
        words.contains(word) && index == input.lastIndex -> result.add(word)
        words.contains(word) -> result.addAll(execute(input, words, index + 1, dp).map { "$word $it" })
      }
    }
    return result.toList().also { dp[start] = it }
  }
}

fun main() {
  runTests(listOf(
      Triple("catsanddog", listOf("cat", "cats", "and", "sand", "dog"), setOf("cats and dog", "cat sand dog")),
      Triple("pineapplepenapple", listOf("apple", "pen", "applepen", "pine", "pineapple"), setOf("pine apple pen apple", "pineapple pen apple", "pine applepen apple")),
      Triple("catsandog", listOf("cats", "dog", "sand", "and", "cat"), setOf())
  )) { (input, words, value) -> value to WordBreakII().execute(input, words).toSet() }
}