package io.matrix

import io.recursion.PascalTriangle
import io.utils.runTests

class GeneratePascalTriangle {

  fun execute(numRows: Int): List<List<Int>> = when (numRows) {
    0 -> emptyList()
    1 -> listOf(listOf(1))
    2 -> listOf(listOf(1), listOf(1, 1))
    else -> {
      val result = mutableListOf(listOf(1), listOf(1, 1))
      (1 until numRows - 1).forEach { index ->
        val last = result[index]
        val row = IntArray(last.size + 1) { 1 }
        (0 until last.size - 1).forEach { row[it + 1] = last[it] + last[it + 1] }
        result.add(row.toList())
      }
      result
    }
  }
}

fun main() {
  runTests((1 until 20).map { number -> number to (0 until number).map { PascalTriangle().executeIterative(it) } }
  ) { (input, value) -> value to GeneratePascalTriangle().execute(input) }
}