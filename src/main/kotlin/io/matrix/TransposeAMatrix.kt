package io.matrix

import io.utils.runTests

// https://leetcode.com/problems/transpose-matrix/
class TransposeAMatrix {

  fun execute(input: Array<IntArray>): Array<IntArray> {
    if (input.isEmpty() || input.first().isEmpty()) return input
    val result = Array(input.first().size) { IntArray(input.size) }

    input.forEachIndexed { col, currentRow ->
      currentRow.forEachIndexed { row, value ->
        result[row][col] = value
      }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)) to listOf(listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9)),
      arrayOf(intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(3, 6)) to listOf(listOf(1, 2, 3), listOf(4, 5, 6)),
      arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)) to listOf(listOf(1, 4), listOf(2, 5), listOf(3, 6)),
      arrayOf(intArrayOf(1, 2, 3)) to listOf(listOf(1), listOf(2), listOf(3))
  )) { (input, value) -> value to TransposeAMatrix().execute(input).map { it.toList() } }
}