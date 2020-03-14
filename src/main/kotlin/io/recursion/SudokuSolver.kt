@file:Suppress("MemberVisibilityCanBePrivate")

package io.recursion

import io.queue.Coordinates

class SudokuSolver {

  fun executeRecursive(board: Array<CharArray>, row: Int = 0, col: Int = 0): Boolean = when {
    row == 8 && col == 9 -> true
    col == 9 -> executeRecursive(board, row + 1, 0)
    else -> {
      println("$row,$col")
      when (board[row][col]) {
        '.' -> getValidValues(board, row, col).let { values ->
          if (values.isEmpty()) {
            false
          } else {
            values.any { value ->
              board[row][col] = value
              executeRecursive(board, row, col + 1).also {
                if (!it) board[row][col] = '.'
              }
            }
          }
        }
        else -> executeRecursive(board, row, col + 1)
      }
    }
  }

  fun getValidValues(board: Array<CharArray>, row: Int, col: Int) =
      (0 until 9).map { (it + 1).toString().first() }.filter { value ->
        when {
          board[row].contains(value) -> false
          board.indices.any { board[it][col] == value } -> false
          generateNeighbors(row, col).any {
            try {
              board[it.i][it.j] == value
            } catch (e: Exception) {
              println("Exception with $row,$col")
              throw e
            }
          } -> false
          else -> true
        }
      }

  fun generateNeighbors(row: Int, col: Int) =
      generateNeighbors(col).let { colNeighbors ->
        generateNeighbors(row).flatMap { newRow ->
          colNeighbors.flatMap { newCol ->
            listOf(Coordinates(newRow, newCol), Coordinates(newRow, col), Coordinates(row, newCol))
          }
        }
      }

  fun generateNeighbors(index: Int) = when (index % 3) {
    0 -> listOf(index + 1, index + 2)
    1 -> listOf(index + 1, index - 1)
    else -> listOf(index - 1, index - 2)
  }
}

fun main() {
  val sudokuSolver = SudokuSolver()
  listOf(
      arrayOf(
          charArrayOf('.', '.', '6', '.', '.', '4', '.', '9', '.'),
          charArrayOf('.', '.', '1', '.', '5', '7', '.', '.', '4'),
          charArrayOf('.', '5', '.', '.', '.', '9', '.', '.', '8'),
          charArrayOf('.', '6', '.', '7', '.', '.', '.', '8', '.'),
          charArrayOf('2', '.', '.', '.', '3', '.', '.', '.', '6'),
          charArrayOf('.', '4', '.', '.', '.', '8', '.', '1', '.'),
          charArrayOf('6', '.', '.', '8', '.', '.', '.', '3', '.'),
          charArrayOf('8', '.', '.', '4', '9', '.', '7', '.', '.'),
          charArrayOf('.', '1', '.', '5', '.', '.', '8', '.', '.')
      )
  ).map { input ->
    println(sudokuSolver.executeRecursive(input))
    input.mapIndexed { i, col ->
      col.map { print("|$it") }
      if (i % 3 == 2) println("|\n|- - -|- - -|- - -|")
      else println("|")
    }
  }
}