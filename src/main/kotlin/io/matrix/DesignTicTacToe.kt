package io.matrix

// https://leetcode.com/problems/design-tic-tac-toe/
class DesignTicTacToe(private val size: Int) {
  private val board = Array(size) { IntArray(size) }


  /** Player {player} makes a move at ({row}, {col}).
  @param row The row of the board.
  @param col The column of the board.
  @param player The player, can be either 1 or 2.
  @return The current winning condition, can be either:
  0: No one wins.
  1: Player 1 wins.
  2: Player 2 wins. */
  fun move(row: Int, col: Int, player: Int): Int {
    board[row][col] = player
    return when {
      winHorizontal(player) || winVertical(player) || winDiagonal(player) || winReverseDiagonal(player) -> player
      else -> 0
    }
  }

  private fun winHorizontal(player: Int): Boolean {
    board.forEach { col ->
      var count = 0
      for (elem in col) {
        if (elem != player) break
        count++
      }
      if (count == size) return true
    }
    return false
  }

  private fun winVertical(player: Int): Boolean {
    for (col in board.indices) {
      var count = 0
      for (row in board.indices) {
        if (board[row][col] != player) break
        count++
      }
      if (count == size) return true
    }
    return false
  }

  private fun winDiagonal(player: Int): Boolean {
    for (index in board.indices) {
      if (board[index][index] != player) return false
    }
    return true
  }

  private fun winReverseDiagonal(player: Int): Boolean {
    for (index in board.indices) {
      if (board[board.lastIndex - index][index] != player) return false
    }
    return true
  }
}

fun main() {
  val designTicTacToe = DesignTicTacToe(2)
  designTicTacToe.move(0, 1, 1)
  designTicTacToe.move(1, 1, 2)
  designTicTacToe.move(1, 0, 1)
}