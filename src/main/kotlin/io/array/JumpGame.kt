package io.array

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3310/
class JumpGame {

  fun execute(input: IntArray, index: Int = 0, visited: MutableSet<Int> = mutableSetOf()): Boolean = when {
    input.isEmpty() -> true
    index == input.size - 1 -> true
    index !in input.indices -> false
    input[index] == 0 || visited.contains(index) -> false
    else -> {
      var current = input[index]
      visited.add(index)
      var found = false
      while (current > 0) {
        if (execute(input, index + current, visited)) {
          found = true
          break
        }
        current--
      }
      found
    }
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(2, 3, 1, 1, 4) to true,
      intArrayOf(3, 2, 1, 0, 4) to false,
      intArrayOf(1, 2, 3) to true
  )) { (input, value) -> value to JumpGame().execute(input) }
}