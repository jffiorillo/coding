@file:Suppress("MemberVisibilityCanBePrivate")

package io.recursion

import io.queue.Coordinates

class SudokuSolver {

  fun executeRecursive(board: Array<CharArray>, row: Int = 0, col: Int = 0): Boolean = when {
    row == 8 && col == 9 -> true
    col == 9 -> executeRecursive(board, row + 1, 0)
    else -> {
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
      (0 until 9).map { (it + 1).toString().first() }.filter { value -> isValid(board, row, col, value) }

  fun isValid(board: Array<CharArray>, pos: Int, value: Char) = isValid(board, pos / 9, pos % 9, value)

  fun isValid(board: Array<CharArray>, row: Int, col: Int, value: Char) = when {
    board[row].contains(value) -> false
    board.indices.any { board[it][col] == value } -> false
    generateNeighbors(row, col).any { board[it.i][it.j] == value } -> false
    else -> true
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

  fun executeIterative(board: Array<CharArray>): Unit {
    val stack = mutableListOf<Int>()
    var pos: Int = -1
    var char: Char

    pos = nextPos(board, pos)
    while (pos != -1) {
      char = findNextValidChar(board, pos)
      if (char == '.') {
        // Cannot find valid char, backtrack to last position
        board[pos / 9][pos % 9] = '.'
        pos = stack.popValue()
      } else {
        // Found a valid char, set it on the board
        // Push current position to stack for later back tracking
        // Finally, advance to next position
        board[pos / 9][pos % 9] = char
        stack.add(pos)
        pos = nextPos(board, pos)
      }
    }
    println("${stack.size}")
  }

  fun MutableList<Int>.popValue(): Int =
      if (this.isEmpty()) -1 else this.removeAt(this.count() - 1)

  fun findNextValidChar(board: Array<CharArray>, pos: Int): Char {
    var char = nextChar(board[pos / 9][pos % 9])
    if (char == '.') return '.'
    while (!isValid(board, pos, char)) {
      char = nextChar(char)
      if (char == '.') {
        break
      }
    }
    return char
  }

  fun nextChar(char: Char): Char = when (char) {
    '9' -> '.'
    '.' -> '1'
    else -> char + 1
  }

  fun nextPos(board: Array<CharArray>, pos: Int): Int =
      (pos + 1..80).firstOrNull { board[it / 9][it % 9] == '.' } ?: -1
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
  ).map { board ->
    sudokuSolver.findNextValidChar(board, 0)
    sudokuSolver.executeIterative(board)
    board.mapIndexed { i, col ->
      col.map { print("|$it") }
      if (i % 3 == 2) println("|\n|- - -|- - -|- - -|")
      else println("|")
    }
  }
}