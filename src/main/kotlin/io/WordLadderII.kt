package io

import io.utils.runTests
import java.util.*

//https://leetcode.com/problems/word-ladder-ii/description/

class WordLadderII {

  // WIP , not working yet
  fun execute(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
    val list = arrayListOf<List<String>>()
    val queue = LinkedList<MutableList<String>>()
    queue.add(LinkedList(listOf(beginWord)))
    val visited = mutableSetOf<String>()
    var minSize: Int? = null
    while (!queue.isEmpty()) {
      val currentList = queue.pop()
      val lastElement = currentList.last()
      if (!visited.contains(lastElement)) {
        visited.add(lastElement)
        val different = different(endWord, lastElement)
        if (different < 2) {
          val newPath = LinkedList(currentList).apply { add(endWord) }
          when {
            minSize == null -> {
              list.add(newPath)
              minSize = newPath.size
            }
            newPath.size == minSize -> {
              list.add(newPath)
            }
          }
        }
        if (minSize == null || currentList.size < minSize) {
          val childOf = childOf(lastElement, wordList)
          queue.addAll(childOf.filter { it != endWord }
              .filter { !visited.contains(it) }
              .map { LinkedList(currentList).also { l -> l.add(it) } })
        }
      }
    }
    return list
  }
}

private fun different(word1: String, word2: String) = word1.zip(word2).fold(word1.length) { acc, pair -> acc - (pair.first == pair.second).toInt() }

private fun childOf(word: String, wordList: List<String>) = wordList.filter { different(word, it) < 2 }

fun Boolean.toInt() = if (this) 1 else 0
private data class Helper(val startWord: String, val endWord: String, val dictionary: List<String>, val value: List<List<String>>)

fun main() {
  runTests(listOf(
      Helper("hit", "cog", listOf("hot", "dot", "dog", "lot", "log"), listOf(listOf("hit", "hot", "dot", "dog", "cog"), listOf("hit", "hot", "lot", "log", "cog"))),
      Helper("a", "b", listOf("a", "b", "c"), listOf(listOf("a", "c")))
  )) { (startWord, endWord, dictionary, value) -> value to WordLadderII().execute(startWord, endWord, dictionary) }
}