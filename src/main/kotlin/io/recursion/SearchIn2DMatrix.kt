package io.recursion

import io.queue.Coordinates

// https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2872/
class SearchIn2DMatrix {

  fun execute(
      matrix: Array<IntArray>, search: Int,
      top: Coordinates = Coordinates(0, 0),
      bottom: Coordinates = Coordinates(matrix.size - 1, (matrix.lastOrNull()?.size ?: 1) - 1)): Boolean = when {
    matrix.isEmpty() -> false
    matrix.size == 1 && matrix.first().size == 1 -> matrix.first().first() == search
    else -> {
      val xPivot = (bottom.i - top.i) / 2
      (top.j..bottom.j).map { index ->
        when {
          matrix[xPivot][index] < search -> null
          matrix[xPivot][index] == index -> Integer.MIN_VALUE
          else -> index
        }
      }.filterNotNull().firstOrNull()?.let { index ->
        when (index) {
          Int.MIN_VALUE -> true
          else -> {
            execute(matrix, search, Coordinates(top.i, index), Coordinates(xPivot, bottom.j)) ||
                execute(matrix, search, Coordinates(xPivot, top.j), Coordinates(bottom.i, index))
          }
        }
      } ?: false
    }
  }.also {
    println("WIP not solved yet")
  }
}