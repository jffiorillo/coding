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

  fun executeWithTree(input: Array<String>): String {
    val root = Trie()
    input.forEach { root.insert(it) }
    var current = root
    var result = ""
    while (true) {
      current.value?.let { result += it }
      if (current.finishWord || current.children.size != 1) break
      current = current.children.first()
    }
    return result
  }

  data class Trie(val value: Char? = null, val children: MutableList<Trie> = mutableListOf(), var finishWord:Boolean = false) {
    fun insert(value: String) {
      value.fold(this) { current, char ->
        current.children.firstOrNull { it.value == char } ?: Trie(char).also { current.children.add(it) }
      }.finishWord = true
    }
  }
}

fun main() {
  runTests(listOf(
      listOf("hello", "he", "hell") to "he",
      listOf("flower","flow","flight") to "fl"
  )) { (input, value) -> value to LongestCommonPrefix().executeWithTree(input.toTypedArray()) }
}