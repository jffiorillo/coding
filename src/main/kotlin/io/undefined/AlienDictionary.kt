package io.undefined

import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/alien-dictionary/
class AlienDictionary {

  fun execute(input: Array<String>): String {
    if (input.isEmpty()) return ""
    val letters = input.fold(mutableSetOf<Char>()) { acc, word -> acc.apply { word.forEach { add(it) } } }
    val maxSize = input.fold(0) { acc, word -> maxOf(acc, word.length) }
    val result = mutableListOf<Char>()
    var prefix = -1
    val implications = mutableSetOf<Pair<Char, Char>>()
    while (prefix < maxSize) {
      input.groupByPrefix(prefix).forEach { list ->
        var index = 0
        if (index == list.lastIndex && result.isEmpty()) result.add(list.first())
        while (index < list.lastIndex) {
          val current = list[index]
          val next = list[index + 1]
          when {
            result.contains(next) && result.contains(current) && result.indexOf(current) > result.indexOf(next) -> {
              return ""
            }
            result.contains(next) && result.contains(current) -> {
            }
            result.contains(next) -> result.add(result.indexOf(next), current)
            result.contains(current) -> result.add(result.indexOf(current) + 1, next)
            result.isEmpty() -> {
              result.add(current)
              result.add(next)
            }
            else -> {
              implications.add(current to next)
            }
          }
          index++
        }
      }
      prefix++
    }
    when {
      implications.size == 1 -> {
        implications.first().let { (current, next) ->
          when {
            result.contains(current) && result.contains(next) -> {
            }
            result.contains(next) -> result.add(result.indexOf(next), current)
            result.contains(current) -> result.add(result.indexOf(current) + 1, next)
            else -> {
              result.add(current)
              result.add(next)
            }
          }
        }
      }
      implications.isNotEmpty() && result.size < letters.size -> {
        var first = true
        repeat(2) {
          implications.forEach { (current, next) ->
            when {
              result.contains(current) && result.contains(next) && result.indexOf(current) + 1 != result.indexOf(next) -> {
                return ""
              }
              result.contains(next) -> result.add(result.indexOf(next), current)
              result.contains(current) -> result.add(result.indexOf(current) + 1, next)
              !first -> {
                result.add(current)
                result.add(next)
              }
            }
          }
          first = false
        }
      }
    }
    if (result.size < letters.size) {
      letters.filter { !result.contains(it) }.forEach { result.add(it) }
    }
    return result.joinToString(separator = "") { it.toString() }
  }

  // https://leetcode.com/problems/alien-dictionary/solution/
  fun alienOrder(words: Array<String>): String? {

    // Step 0: Create data structures and find all unique letters.
    val adjList: MutableMap<Char, MutableList<Char>> = HashMap()
    val counts: MutableMap<Char, Int> = HashMap()
    for (word in words) {
      for (c in word.toCharArray()) {
        counts[c] = 0
        adjList[c] = ArrayList()
      }
    }

    // Step 1: Find all edges.
    for (i in 0 until words.lastIndex) {
      val word1 = words[i]
      val word2 = words[i + 1]
      // Check that word2 is not a prefix of word1.
      if (word1.length > word2.length && word1.startsWith(word2)) return ""
      // Find the first non match and insert the corresponding relation.
      for (j in 0 until minOf(word1.length, word2.length)) {
        if (word1[j] != word2[j]) {
          adjList.getValue(word1[j]).add(word2[j])
          counts[word2[j]] = counts.getValue(word2[j]) + 1
          break
        }
      }
    }

    // Step 2: Breadth-first search.
    val sb = StringBuilder()
    val queue: Queue<Char> = LinkedList()
    for (c in counts.keys) {
      if (counts[c] == 0) {
        queue.add(c)
      }
    }
    while (!queue.isEmpty()) {
      val c = queue.remove()
      sb.append(c)
      for (next in adjList.getValue(c)) {
        counts[next] = counts.getValue(next) - 1
        if (counts[next] == 0) {
          queue.add(next)
        }
      }
    }
    return if (sb.length < counts.size) "" else sb.toString()
  }
}

private fun Array<String>.groupByPrefix(prefixIndex: Int): List<List<Char>> = mutableListOf<MutableList<Char>>().let { result ->
  if (this.isEmpty())
    return result
  var previousPrefix: String? = null
  this.forEach { word ->
    val range = 0..prefixIndex
    when {
      prefixIndex + 1 >= word.length -> {
      }
      previousPrefix == word.substring(range) && result.isNotEmpty() && result.last().last() == word[prefixIndex + 1] -> {
      }
      previousPrefix == word.substring(range) -> result.last().add(word[prefixIndex + 1])
      else -> {
        result.add(mutableListOf(word[prefixIndex + 1]))
        previousPrefix = word.substring(range)
      }
    }
  }
  result
}


fun main() {
  runTests(listOf(
      arrayOf("wrt",
          "wrf",
          "er",
          "ett",
          "rftt") to "wertf",
      arrayOf("z", "z") to "z",
      arrayOf("zy", "zx") to "zyx",
      arrayOf("ab", "adc") to "abdc",
      arrayOf("aa","abb","aba") to "",
      arrayOf("a", "b", "ca", "cc") to "abc",
      arrayOf("za","zb","ca","cb") to "zcab"

  )) { (input, value) -> value to AlienDictionary().execute(input) }
}