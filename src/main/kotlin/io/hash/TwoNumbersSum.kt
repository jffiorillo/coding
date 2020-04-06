package io.hash

import io.utils.runTests

// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1115/
class TwoNumbersSum {

  fun execute(input: IntArray, target: Int): IntArray {
    val inputIndexed = input.mapIndexed { index, value -> index to value }
    val map = input.toSet().map { value -> value to inputIndexed.filter { it.second == target - value } }.toMap()
    return inputIndexed
        .first { (index, item) -> map.getValue(item).any { (iIndex, value) -> index != iIndex && map.containsKey(value) } }.second
        .let { result ->
          map.getValue(result).first().let { (index, rest) ->
            intArrayOf(index, map.getValue(rest).first { (iIndex, value) -> index != iIndex && map.containsKey(value) }.first)
          }
        }
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(3, 3), 6, intArrayOf(0, 1))
  ), evaluation = { a, b -> a.toList() == b.toList() }
      , valueString = { it.toList().toString() }
      , outputString = { it.toList().toString() }
  ) { (input, target, value) -> value to TwoNumbersSum().execute(input, target) }
}