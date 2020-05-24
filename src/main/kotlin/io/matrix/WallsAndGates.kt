package io.matrix

import io.utils.runTests

// https://leetcode.com/problems/walls-and-gates/
class WallsAndGates {

  fun execute(input: Array<IntArray>) {
    if (input.isEmpty() || input.first().isEmpty()) return
    val gates = mutableListOf<Pair<Int, Int>>()
    input.forEachIndexed { row, column ->
      column.forEachIndexed { col, value ->
        when (value) {
          0 -> gates.add(row to col)
        }
      }
    }

    for (current in gates) {
      val visited = mutableListOf<Pair<Int, Int>>()
      var stack = generateChild(input, current, visited)
      var distance = 1
      while (stack.isNotEmpty()) {
        stack = stack.flatMap { (row, col) ->
          when {
            visited.contains(row to col) -> emptyList()
            distance < input[row][col] -> {
              visited.add(row to col)
              input[row][col] = distance
              generateChild(input, row to col, visited)
            }
            else -> {
              visited.add(row to col)
              emptyList()
            }
          }
        }
        distance++
      }
    }

  }

  fun generateChild(input: Array<IntArray>, coordinates: Pair<Int, Int>, visited: Collection<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    val invalids = listOf(0, -1)
    if (coordinates.first + 1 < input.size && input[coordinates.first + 1][coordinates.second] !in invalids && !visited.contains(coordinates.first + 1 to coordinates.second))
      result.add(coordinates.first + 1 to coordinates.second)
    if (coordinates.first - 1 >= 0 && input[coordinates.first - 1][coordinates.second] !in invalids && !visited.contains(coordinates.first - 1 to coordinates.second))
      result.add(coordinates.first - 1 to coordinates.second)
    if (coordinates.second + 1 < input.first().size && input[coordinates.first][coordinates.second + 1] !in invalids && !visited.contains(coordinates.first to coordinates.second + 1))
      result.add(coordinates.first to coordinates.second + 1)
    if (coordinates.second - 1 >= 0 && input[coordinates.first][coordinates.second - 1] !in invalids && !visited.contains(coordinates.first to coordinates.second - 1))
      result.add(coordinates.first to coordinates.second - 1)
    return result
  }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(Int.MAX_VALUE, -1, 0, Int.MAX_VALUE),
          intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, -1),
          intArrayOf(Int.MAX_VALUE, -1, Int.MAX_VALUE, -1),
          intArrayOf(0, -1, Int.MAX_VALUE, Int.MAX_VALUE)
      ) to listOf(
          listOf(3, -1, 0, 1),
          listOf(2, 2, 1, -1),
          listOf(1, -1, 2, -1),
          listOf(0, -1, 3, 4)
      )
  )) { (input, value) -> value to input.also { WallsAndGates().execute(input) }.map { it.toList() }.toList() }
}