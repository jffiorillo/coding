@file:Suppress("MemberVisibilityCanBePrivate")

package io.recursion

import kotlin.math.abs
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class NQueens {

  fun execute(size: Int): Int = when (size) {
    0 -> 0
    else -> execute(IntArray(size))
  }

  private fun execute(columns: IntArray, row: Int = 0): Int = when (row) {
    columns.size -> 1
    else ->
      columns.indices.fold(0) { acc, col ->
        if (isValid(columns, row, col)) {
          columns[row] = col
          acc + execute(columns, row + 1)
        } else acc
      }
  }

  private fun isValid(columns: IntArray, newRow: Int, newCol: Int): Boolean =
      (0 until newRow).fold(true) { acc, row ->
        columns[row].let { col ->
          acc &&
              col != newCol &&
              abs(row - newRow) != abs(col - newCol)
        }
      }
}

@ExperimentalTime
fun main() {
  val nQueens = NQueens()
  val numbers = 1
  var acc = Duration.ZERO
  repeat(numbers) {
    measureTime { print(nQueens.execute(15)) }.let { time ->
      acc += time
      print("-$time ")
    }
  }
  println("\ntotal: $acc avg: ${acc / numbers}")
}