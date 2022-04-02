package io.undefined

import io.utils.printlnIfDebug
import io.utils.runTests

/**
 * Conway's Game of Life is a cellular automaton that is played on a 2D square grid.
 * Each square (or "cell") on the grid can be either alive or dead, and they evolve according to the following rules:
 *  * Any live cell with fewer than two live neighbours dies (referred to as underpopulation).
 *  * Any live cell with more than three live neighbours dies (referred to as overpopulation).
 *  * Any live cell with two or three live neighbours lives, unchanged, to the next generation.
 *  * Any dead cell with exactly three live neighbours comes to life.
 * https://conwaylife.com/
 */

class GameOfLife {

  fun nextGeneration(input: List<MutableList<Int>>, debug: Boolean): List<MutableList<Int>> =
    input.map { column -> column.map { it }.toMutableList() }.apply {
      forEachIndexed { x, column ->
        column.indices.forEach { y ->
          val aliveCells = checkNeighbor(input, x, y, debug)
          if (column[y] == 0 && aliveCells == 3) column[y] =
            1.also { printlnIfDebug("column[$x][$y] was 0 now is 1, aliveCells: $aliveCells", debug) }
          else if (column[y] == 1 && aliveCells != 2 && aliveCells != 3) column[y] =
            0.also { printlnIfDebug("column[$x][$y] was 1 now is 0, aliveCells: $aliveCells", debug) }
          else {
            printlnIfDebug("not modifying column[$x][$y] = ${column[y]} aliveCells: $aliveCells", debug)
          }
        }
      }
    }

  private fun checkNeighbor(input: List<MutableList<Int>>, x: Int, y: Int, debug: Boolean): Int {
    var aliveNeighbor = 0
    printlnIfDebug("checkNeighbor: x $x y $y", debug)
    if (x != 0) aliveNeighbor += checkNeighborInColumn(
      input[x - 1],
      y,
      true
    ).also { printlnIfDebug("checkNeighborInColum: column[${x - 1} (up)][$y] aliveNeighborInColumn $it", debug) }
    aliveNeighbor += checkNeighborInColumn(
      input[x],
      y,
      false
    ).also { printlnIfDebug("checkNeighborInColum: column[$x (same)][$y] aliveNeighborInColumn $it", debug) }
    if (x + 1 < input.size) aliveNeighbor += checkNeighborInColumn(
      input[x + 1],
      y,
      true
    ).also { printlnIfDebug("checkNeighborInColum: column[${x + 1}(down)][$y] aliveNeighborInColumn $it", debug) }
    printlnIfDebug("checkNeighbor: column[$x][$y] total aliveNeighbor: $aliveNeighbor", debug)
    return aliveNeighbor
  }

  private fun checkNeighborInColumn(input: MutableList<Int>, y: Int, countY: Boolean): Int {
    var aliveNeighbor = 0
    if (y != 0 && input[y - 1] == 1) aliveNeighbor++
    if (countY && input[y] == 1) aliveNeighbor++
    if (y + 1 < input.size && input[y + 1] == 1) aliveNeighbor++
    return aliveNeighbor
  }
}

fun main() {
  val gameOfLife = GameOfLife()
  runTests(
    listOf(
      Triple(
        listOf(
          mutableListOf(1, 1, 0, 0, 0),
          mutableListOf(0, 1, 0, 1, 0),
          mutableListOf(0, 0, 0, 1, 1),
          mutableListOf(0, 0, 0, 1, 1),
        ),
        listOf(
          mutableListOf(1, 1, 1, 0, 0),
          mutableListOf(1, 1, 0, 1, 1),
          mutableListOf(0, 0, 0, 0, 0),
          mutableListOf(0, 0, 0, 1, 1),
        ), false
      )
    )
  ) { (input, value, debug) -> value to gameOfLife.nextGeneration(input, debug) }
}