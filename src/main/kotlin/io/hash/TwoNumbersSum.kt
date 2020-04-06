package io.hash

import io.utils.runTests

// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1115/
class TwoNumbersSum {

  fun execute(input: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    input.forEachIndexed { index, value ->
      val left = target - value
      if (map.contains(left)) {
        return intArrayOf(index, map.getValue(left))
      }
      map[value] = index
    }
    throw IllegalArgumentException("No two sum solution")
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(3, 3), 6, intArrayOf(0, 1))
  ), evaluation = { a, b -> a.toSet() == b.toSet() }
      , valueString = { it.toList().toString() }
      , outputString = { it.toList().toString() }
  ) { (input, target, value) -> value to TwoNumbersSum().execute(input, target) }
}