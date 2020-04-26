package io.dp

import io.utils.runTests

// https://ianna1009.gitbooks.io/leectcode/Cracking%20Coding%20Interview/Chapter8.%20Recursion%20and%20Dynamic%20Programming/82_robot_in_a_grid.html
class RobotInAGrid {

  fun execute(
      input: Array<IntArray>,
      current: Pair<Int, Int> = Pair(input.size - 1, input.firstOrNull()?.size?.let { it - 1 } ?: 0)
  ): MutableList<Pair<Int, Int>>? = when {
    current == 0 to 0 -> {
      mutableListOf<Pair<Int, Int>>().apply { add(0 to 0) }
    }
    current.first !in input.indices || current.second !in input.first().indices -> null
    input[current.first][current.second] != 0 -> null
    else -> {
      execute(input, current.first - 1 to current.second)?.let { it.apply { add(current) } }
          ?: execute(input, current.first to current.second - 1)?.let { it.apply { add(current) } }
    }
  }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(42, 0, 0, 0),
          intArrayOf(0, 1, 0, 0),
          intArrayOf(0, 0, 1, 0),
          intArrayOf(0, 1, 0, 1),
          intArrayOf(0, 0, 0, 0))
          to listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0), Pair(4, 0), Pair(4, 1), Pair(4, 2), Pair(4, 3)),
      arrayOf(intArrayOf()) to null
  )) { (input, value) -> value to RobotInAGrid().execute(input) }
}