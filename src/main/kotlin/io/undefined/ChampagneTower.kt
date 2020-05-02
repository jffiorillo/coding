package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/champagne-tower/
class ChampagneTower {

  fun execute(poured: Int, row: Int, col: Int): Double {
    val result = DoubleArray(row + 1) { i -> if (i == 0) poured.toDouble() else 0.toDouble() }
    var currentRow = 1
    while (currentRow <= row) {
      var temp = 0.toDouble()
      var isEmpty = false
      (0..currentRow).map { it to result[it] }.forEach { (index, value) ->
        if (value > 0) isEmpty = false
        val newTemp = if (value < 1) 0.toDouble() else (value - 1) / 2
        result[index] = temp + newTemp
        temp = newTemp
      }
      if (isEmpty) break
      currentRow++
    }
    return result[col].let { if (it > 1) 1.toDouble() else it }
  }
}

private data class Content(val poured: Int, val row: Int, val col: Int, val value: Double)

fun main() {
  runTests(listOf(
      Content(4, 2, 0, 1.toDouble() / 4),
      Content(4, 2, 1, 1.toDouble() / 2),
      Content(14, 3, 0, 7.toDouble() / 8),
      Content(14, 3, 1, 25.toDouble() / 8),
      Content(14, 3, 2, 25.toDouble() / 8),
      Content(14, 3, 3, 7.toDouble() / 8),
      Content(6, 2, 0, 0.75)
  )) { (input, row, col, value) -> value to ChampagneTower().execute(input, row, col) }
}