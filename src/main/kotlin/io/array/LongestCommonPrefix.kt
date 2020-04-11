package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1162/
class LongestCommonPrefix {

  fun execute(input: Array<String>): String {
    var commonPrefix = ""
    val minLength = input.fold(null as Int?) { acc, value -> acc?.let { minOf(it, value.length) } ?: value.length } ?: 0

    for (index in 0 until minLength) {
      if (!input.fold(true) { acc, value -> acc && value[index] == input.first()[index] })
        break
      else commonPrefix += input.first()[index]
    }
    return commonPrefix
  }
}

fun main() {
  runTests(listOf((listOf("hello", "he", "hell") to "he"))) { (input, value) -> value to LongestCommonPrefix().execute(input.toTypedArray()) }
}