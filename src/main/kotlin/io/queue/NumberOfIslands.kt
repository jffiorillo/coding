package io.queue

import java.util.*
import kotlin.collections.HashSet

// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1374/
class NumberOfIslands {

  fun execute(grid: Array<CharArray>): Int {
    val visited = HashSet<Coordinates>()
    var numberOfIslands = 0
    grid.mapIndexed { index, chars ->
      chars.mapIndexed { internalIndex, char ->
        val coordinates = Coordinates(index, internalIndex)
        if (!visited.contains(coordinates)) {
          visited.add(coordinates)
          if (char != '0') {
            numberOfIslands += 1
            visitIsland(grid, coordinates, visited, grid.size, chars.size)
          }
        }
      }
    }
    return numberOfIslands
  }

  private fun visitIsland(grid: Array<CharArray>, root: Coordinates, visited: HashSet<Coordinates>, maxI: Int, maxJ: Int) {
    val stack = Stack<List<Coordinates>>()
    stack.push(listOf(root))
    while (stack.isNotEmpty()) {
      val coordinates = stack.pop()
      coordinates.map { coordinate ->
        if (!visited.contains(coordinate)) {
          visited.add(coordinate)
        }
        if (grid[coordinate.i][coordinate.j] != '0') {
          coordinate.generateChild(maxI, maxJ).filter { !visited.contains(it) }.let {
            if (it.isNotEmpty()) {
              stack.push(it)
            }
          }
        }
      }
    }
  }
}

private data class Coordinates(val i: Int, val j: Int) {

  fun generateChild(maxI: Int, maxJ: Int) =
      mutableListOf<Coordinates>().apply {
        if (i + 1 < maxI) {
          add(Coordinates(i + 1, j))
        }
        if (j + 1 < maxJ) {
          add(Coordinates(i, j + 1))
        }
        if (i - 1 >= 0) {
          add(Coordinates(i - 1, j))
        }
        if (j - 1 >= 0) {
          add(Coordinates(i, j - 1))
        }
      }
}

fun main() {
  val numberOfIslands = NumberOfIslands()
  listOf(
//      arrayOf(
//          "11110",
//          "11010",
//          "11000",
//          "00000"),
//      arrayOf(
//          "11000",
//          "11000",
//          "00100",
//          "00011"),
      arrayOf(
          "111",
          "010",
          "111")
  ).map { grid ->
    println(" ${numberOfIslands.execute(grid.map { it.toCharArray() }.toTypedArray())}")

  }
}