package io.math

import io.utils.runTests


// https://leetcode.com/problems/merge-intervals/
class MergeIntervals {

  fun execute(input: Array<IntArray>): Array<IntArray> {
    if (input.isEmpty()) return emptyArray()
    input.sortBy { it.first() }
    var index = 1
    val result = mutableListOf(input.first())
    while (index < input.size) {
      val value = result.last()
      val next = input[index]
      if (value[1] >= next.first()) {
        result[result.lastIndex] = intArrayOf(value.first(), maxOf(value[1], next[1]))
      } else {
        result.add(next)
      }
        index++
    }
    return result.toTypedArray()
  }
}

fun main() {
  runTests(listOf(
      arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)) to setOf(listOf(1, 6), listOf(8, 10), listOf(15, 18))
  )) { (input, value) -> value to MergeIntervals().execute(input).map { it.toList() }.toSet() }
}