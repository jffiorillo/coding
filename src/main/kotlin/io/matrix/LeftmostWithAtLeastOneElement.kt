package io.matrix

import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3306/
class LeftmostWithAtLeastOneElement {

  fun execute(input: BinaryMatrix): Int {
    val (row, col) = input.dimensions()

    var currentRow = 0
    var currentCol = col - 1
    var result = -1
    while (currentCol in 0 until col && currentRow in 0 until row) {
      when (input.get(currentRow, currentCol)) {
        1 -> {
          result = if (result != -1) minOf(result, currentCol) else currentCol
          currentCol--
        }
        else -> {
          currentRow++
        }
      }
    }
    return result
  }
}

data class BinaryMatrix(private val matrix: List<List<Int>>) {
  fun get(row: Int, col: Int): Int = matrix[row][col]
  fun dimensions(): List<Int> = listOf(matrix.size, matrix.firstOrNull()?.size ?: 0)
}

fun main() {
  runTests(listOf(
      BinaryMatrix(listOf(listOf(0, 1), listOf(0, 1))) to 1,
      BinaryMatrix(listOf(listOf(0, 0), listOf(1, 1))) to 0
  )) { (input, value) -> value to LeftmostWithAtLeastOneElement().execute(input) }
}