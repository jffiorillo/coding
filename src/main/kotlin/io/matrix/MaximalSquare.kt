package io.matrix

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3312/
class MaximalSquare {

  fun execute(input: Array<IntArray>): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    var result = 0
    input.forEachIndexed { index, row ->
      row.forEachIndexed { col, value ->
        if (!visited.contains(index to col)) {
          visited.add(index to col)
          if (value == 1) {
            result = maxOf(result, findSize(input, index, col, visited))
          }
        }
      }
    }
    return result
  }

  private fun findSize(input: Array<IntArray>, row: Int, col: Int, visited: MutableSet<Pair<Int, Int>>): Int {
    var result = 1
    var offset = 1
    loop@ while (true) {
      val offsetCol = col + offset
      val offsetRow = row + offset
      if (offsetCol >= input.first().size || offsetRow >= input.size) {
        break
      }
      for (r in 0..offset) {
        val currentRow = row + r
        if (input[currentRow][offsetCol] != 1) {
          visited.add(currentRow to offsetCol)
          break@loop
        }
      }
      for (c in 0 until offset) {
        val currentCol = col + c
        if (input[offsetRow][currentCol] != 1) {
          visited.add(offsetRow to currentCol)
          break@loop
        }
      }
      result = (offset + 1) * (offset + 1)
      offset++
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(1, 0, 1, 0, 0),
          intArrayOf(1, 0, 1, 1, 1),
          intArrayOf(1, 1, 1, 1, 1),
          intArrayOf(1, 0, 0, 1, 0)
      ) to 4,
      arrayOf(
          intArrayOf(1, 1, 1, 0, 0),
          intArrayOf(1, 1, 1, 1, 1),
          intArrayOf(1, 1, 1, 1, 1),
          intArrayOf(1, 0, 1, 1, 1)
      ) to 9,
      arrayOf(
          intArrayOf(1, 1, 1, 1, 0),
          intArrayOf(1, 1, 1, 1, 1),
          intArrayOf(1, 1, 1, 1, 1),
          intArrayOf(1, 1, 1, 1, 1)
      ) to 16,
      arrayOf(
          intArrayOf(1, 1, 1, 1, 0)
      ) to 1,
      arrayOf(
          intArrayOf(1)
      ) to 1,
      arrayOf(
          intArrayOf(0)
      ) to 0,
      arrayOf(
          intArrayOf()
      ) to 0
  )) { (input, value) -> value to MaximalSquare().execute(input) }
}