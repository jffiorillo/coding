package io.matrix

import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/island-perimeter/
class IslandPerimeter {

  fun execute(input: Array<IntArray>): Int {
    input.forEachIndexed { row, rowValue ->
      rowValue.forEachIndexed { col, value ->
        if (value == 1) return calculateIslandPerimeter(input, row, col)
      }
    }
    return 0
  }

  private fun calculateIslandPerimeter(input: Array<IntArray>, initialRow: Int, initialCol: Int): Int {
    var result = 0
    val visited = mutableSetOf<Pair<Int, Int>>()
    val stack = LinkedList<Pair<Int, Int>>().apply { add(initialRow to initialCol) }
    while (stack.isNotEmpty()) {
      val (row, col) = stack.pop()
      if (!visited.contains(row to col)) {
        visited.add(row to col)
        result += calculatePerimeter(input, row, col)
        generateNeighbors(input, row, col, visited).forEach { stack.push(it) }
      }
    }
    return result
  }

  private fun calculatePerimeter(input: Array<IntArray>, row: Int, col: Int): Int {
    var result = 0
    if (row - 1 < 0 || input[row - 1][col] == 0) result++
    if (row + 1 == input.size || input[row + 1][col] == 0) result++
    if (col - 1 < 0 || input[row][col - 1] == 0) result++
    if (col + 1 == input.first().size || input[row][col + 1] == 0) result++
    return result
  }

  private fun generateNeighbors(input: Array<IntArray>, row: Int, col: Int, visited: Set<Pair<Int, Int>>): List<Pair<Int, Int>> =
      mutableListOf<Pair<Int, Int>>().apply {
        if (!visited.contains(row - 1 to col) && row - 1 >= 0 && input[row - 1][col] == 1) add(row - 1 to col)
        if (!visited.contains(row + 1 to col) && row + 1 < input.size && input[row + 1][col] == 1) add(row + 1 to col)
        if (!visited.contains(row to col - 1) && col - 1 >= 0 && input[row][col - 1] == 1) add(row to col - 1)
        if (!visited.contains(row to col + 1) && col + 1 < input.first().size && input[row][col + 1] == 1) add(row to col + 1)
      }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(0, 1, 0, 0),
          intArrayOf(1, 1, 1, 0),
          intArrayOf(0, 1, 0, 0),
          intArrayOf(1, 1, 0, 0)
      ) to 16
  )) { (input, value) -> value to IslandPerimeter().execute(input) }
}