package io.matrix

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1167/
class MatrixDiagonalTraversal {

  fun execute(matrix: Array<IntArray>): IntArray = when {
    matrix.isEmpty() -> intArrayOf()
    matrix.size == 1 -> matrix.first()
    matrix.first().size == 1 ->
      matrix.map { it.first() }.toIntArray()
    else -> {
      val maxRow = matrix.size
      val maxCol = matrix.firstOrNull()?.size ?: 0
      val result = IntArray(maxRow * maxCol) { 0 }
      val resultIndex = traverseColumns(maxRow, matrix, maxCol, result)
      traverseInverseRows(maxRow, matrix, maxCol, result, resultIndex)
      result
    }
  }

  private fun traverseColumns(maxRow: Int, matrix: Array<IntArray>, maxCol: Int, result: IntArray): Int {
    var traversalPositive = true
    var resultIndex = 0
    for (i in 0 until maxCol) {
      resultIndex = matrix.traversal(
          when {
            traversalPositive -> minOf(i, maxRow - 1)
            else -> 0
          },
          when {
            traversalPositive && i < maxRow -> 0
            traversalPositive && i >= maxRow -> i - (maxRow - 1)
            else -> i
          },
          result, resultIndex, traversalPositive)
      traversalPositive = !traversalPositive
    }
    return resultIndex
  }


  private fun traverseInverseRows(
      maxRow: Int, matrix: Array<IntArray>, maxCol: Int, result: IntArray, resultIndexInput: Int): Int {
    var traversalPositive = maxCol.rem(2) == 0
    var resultIndex = resultIndexInput
    for (i in 1 until maxRow - (maxCol - 1)) {
      resultIndex = matrix.traversal(
          when {
            traversalPositive -> i + maxCol - 1
            else -> i
          },
          when {
            traversalPositive -> 0
            else -> maxCol - 1
          },
          result, resultIndex, traversalPositive)
      traversalPositive = !traversalPositive
    }

    for (i in maxOf(1, maxRow - (maxCol - 1)) until maxRow) {
      resultIndex = matrix.traversal(
          when {
            traversalPositive -> maxRow - 1
            else -> i
          },
          when {
            traversalPositive && maxCol > maxRow -> maxCol - (maxRow - i)
            traversalPositive -> i - (maxRow - maxCol).absoluteValue
            else -> maxCol - 1
          },
          result, resultIndex, traversalPositive)
      traversalPositive = !traversalPositive
    }
    return resultIndex
  }
}

fun Array<IntArray>.traversal(
    rowIndex: Int,
    colIndex: Int,
    output: IntArray,
    startOutputIndex: Int,
    positive: Boolean): Int {
  var row = rowIndex
  var col = colIndex
  var outputIndex = startOutputIndex
  while (row in indices && col in first().indices) {
    output[outputIndex++] = this[row][col]
    if (positive) row-- else row++
    if (positive) col++ else col--
  }
  return outputIndex
}

fun main() {
  runTests(listOf(
      arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)) to listOf(1, 2, 4, 7, 5, 3, 6, 8, 9),
      arrayOf(intArrayOf(2, 3)) to listOf(2, 3),
      arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)) to listOf(1, 2, 3, 4),
      arrayOf(intArrayOf(1), intArrayOf(2)) to listOf(1, 2),
      arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3), intArrayOf(4)) to listOf(1, 2, 3, 4),
      arrayOf(intArrayOf(2, 5), intArrayOf(8, 4), intArrayOf(0, -1)) to listOf(2, 5, 8, 0, 4, -1),
      arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9), intArrayOf(10, 11, 12)) to listOf(1, 2, 4, 7, 5, 3, 6, 8, 10, 11, 9, 12),
      arrayOf(intArrayOf(2, 5), intArrayOf(8, 4), intArrayOf(0, -1), intArrayOf(3, 6)) to listOf(2, 5, 8, 0, 4, -1, 3, 6),
      arrayOf(intArrayOf(1, 2, 3, 4, 5, 6, 7), intArrayOf(8, 9, 10, 11, 12, 13, 14), intArrayOf(15, 16, 17, 18, 19, 20, 21)) to listOf(1, 2, 8, 15, 9, 3, 4, 10, 16, 17, 11, 5, 6, 12, 18, 19, 13, 7, 14, 20, 21),
      arrayOf(intArrayOf(2, 5, 8), intArrayOf(4, 0, -1)) to listOf(2, 5, 4, 0, 8, -1)
  )) { (input, value) -> value to MatrixDiagonalTraversal().execute(input).toList() }
}