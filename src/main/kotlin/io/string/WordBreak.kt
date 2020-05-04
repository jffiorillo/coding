package io.string

import io.utils.runTests
import java.lang.StringBuilder
import java.util.*

// https://leetcode.com/problems/word-break/
class WordBreak {

  fun execute(input: String,
              dictionary: Set<String>,
              index: Int = 0,
              dp: Array<Boolean?> = Array(input.length) { null }): Boolean {
    if (index !in input.indices) return false
    if (dictionary.isEmpty()) return false
    dp[index]?.let { return it }
    val builder = StringBuilder()
    var current = index
    while (current < input.length) {
      builder.append(input[current])
      if (dictionary.contains(builder.toString()) && (current == input.lastIndex || execute(input, dictionary, current + 1, dp)))
        return true.also { dp[index] = it }
      current++
    }
    return false.also { dp[index] = it }
  }
}

fun main() {
  runTests(listOf(
      Triple("leetcode", setOf("leet", "code"), true),
      Triple("applepenapple", setOf("apple", "pen"), true),
      Triple("catsandog", setOf("cats", "dog", "sand", "and", "cat"), false)
  )) { (input, dictionary, value) -> value to WordBreak().execute(input, dictionary) }
}