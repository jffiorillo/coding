package io.array

import io.utils.runTests

// https://leetcode.com/problems/verifying-an-alien-dictionary/
class VerifyIsSorted {

  fun isAlienSorted(words: Array<String>, order: String): Boolean = execute(words, order)

  fun execute(words: Array<String>, order: String): Boolean {
    val orderMap = order.mapIndexed { index, value -> value to index }.toMap()
    words.reduce { acc, item ->
      if (orderMap.isNotOrdered(acc, item)) return false
      item
    }
    return true
  }

  private fun Map<Char, Int>.isNotOrdered(word0: String, word1: String): Boolean = !isOrdered(word0, word1)
  private fun Map<Char, Int>.isOrdered(word0: String, word1: String): Boolean {
    var index = 0
    var found = false
    while (index < word0.length && index < word1.length) {
      val orderCharAcc = this.getValue(word0[index])
      val orderCharItem = this.getValue(word1[index])
      if (orderCharAcc < orderCharItem) {
        found = true
        break
      } else if (orderCharAcc == orderCharItem) {
        index++
      } else {
        return false
      }
    }
    if (!found && word0.length > word1.length) {
      return false
    }
    return true
  }
}

fun main() {
  runTests(listOf(
      Triple(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz", false)
  )) { (input, order, value) ->
    value to VerifyIsSorted().execute(input, order)
  }
}