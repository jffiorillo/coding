package io.matrix

import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/sort-the-matrix-diagonally/

class SortMatrixDiagonally {

  fun execute(input: Array<IntArray>?): Array<IntArray>? {
    val queue = PriorityQueue<Int>()
    if (input?.isEmpty() != false) return input
    (input.size - 2 downTo 0).forEach { row ->
      enqueue(input, row, 0, queue)
      updateMatrix(input, row, 0, queue)
    }
    input.first().indices.forEach { col ->
      enqueue(input, 0, col, queue)
      updateMatrix(input, 0, col, queue)

    }
    return input
  }


  private fun enqueue(mat: Array<IntArray>, row: Int, col: Int, queue: PriorityQueue<Int>): Unit = when {
    row >= mat.size || col >= mat.first().size || row < 0 || col < 0 -> {
    }
    else -> {
      queue.add(mat[row][col])
      enqueue(mat, row + 1, col + 1, queue)
    }
  }

  private fun updateMatrix(mat: Array<IntArray>, row: Int, col: Int, queue: PriorityQueue<Int>): Unit = when {
    row >= mat.size || col > mat.first().size || row < 0 || col < 0 || queue.isEmpty() -> {
    }
    else -> {
      mat[row][col] = queue.poll()
      updateMatrix(mat, row + 1, col + 1, queue)
    }
  }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(3, 3, 1, 1),
          intArrayOf(2, 2, 1, 2),
          intArrayOf(1, 1, 1, 2))
          to
          arrayOf(
              intArrayOf(1, 1, 1, 1),
              intArrayOf(1, 2, 2, 2),
              intArrayOf(1, 2, 3, 3))
  ),
      { i, v -> i.map { it.toList() }.toList() == v?.map { it.toList() }?.toList() }
      , { array -> array.map { it.toList() }.toList().toString() }
      , { array -> array?.map { it.toList() }?.toList().toString() }
  ) { (input, value) ->
    value to SortMatrixDiagonally().execute(input)
  }
}