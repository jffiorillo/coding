package io.matrix

class SnakesAndLadders {

  fun snakesAndLadders(board: Array<IntArray>): Int = executeRecursive(board)

  fun executeRecursive(board: Array<IntArray>): Int {
    val visited = mutableSetOf<Int>()
    var stack = listOf(0)
    var steps = 0
    var found = false
    while (stack.isNotEmpty() && !found) {
      if (stack.any { it == board.size * board.size - 1 }) found = true
      else {
        visited.addAll(stack)
        stack = getNextMovements(board, stack, visited)
        steps++
      }
    }
    return if (found) steps else -1
  }

  fun execute(
      board: Array<IntArray>,
      position: List<Int> = listOf(0),
      visited: Set<Int> = setOf(),
      minSolutionFound: MinSolution = MinSolution(),
      steps: Int = 0): Pair<Int, Set<Int>>? =
      when {
        position.any { board.size * board.size - 1 == it } -> minSolutionFound.createMinValue(steps to visited)
        minSolutionFound.size() < visited.size -> minSolutionFound.value
        else -> {
          println(visited)
          execute(board, getNextMovements(board, position, visited), visited + position, minSolutionFound, steps + 1)
        }
      }

  private fun getNextMovements(board: Array<IntArray>, positionList: List<Int>, visited: Set<Int>) =
      positionList.flatMap { position ->
        (board.size * board.size).let { maxSize ->
          (1..6)
              .map { position + it }
              .filter { it in 0 until maxSize }
              .map { if (board.getValue(it) != -1) board.getValue(it) - 1 else it }
              .filter { !visited.contains(it) }
        }

      }

  private fun Array<IntArray>.getValue(position: Int) = this[toXCoordinate(position)][toYCoordinate(position)]

  private fun Array<IntArray>.toXCoordinate(position: Int) = this.size - (position / this.size) - 1
  private fun Array<IntArray>.toYCoordinate(position: Int) =
      if ((position / this.size).rem(2) == 0) position.rem(this.size) else this.size - position.rem(this.size) - 1

  data class MinSolution(var value: Pair<Int, Set<Int>>? = null) {

    fun size() = value?.first ?: Int.MAX_VALUE

    fun createMinValue(newValue: Pair<Int, Set<Int>>): Pair<Int, Set<Int>> = when {
      size() < newValue.first -> value!!
      else -> newValue.also {
        value = newValue
      }
    }
  }
}

fun main() {
  val snakesAndLadders = SnakesAndLadders()
  listOf(
      arrayOf(
          intArrayOf(-1, -1, -1, -1, -1, -1),
          intArrayOf(-1, -1, -1, -1, -1, -1),
          intArrayOf(-1, -1, -1, -1, -1, -1),
          intArrayOf(-1, 35, -1, -1, 13, -1),
          intArrayOf(-1, -1, -1, -1, -1, -1),
          intArrayOf(-1, 15, -1, -1, -1, -1)) to 4,
      arrayOf(
          intArrayOf(-1, -1, 16, 31, 29, -1),
          intArrayOf(10, 20, -1, -1, 9, -1),
          intArrayOf(7, -1, -1, -1, 10, 28),
          intArrayOf(-1, 16, -1, 16, -1, -1),
          intArrayOf(28, -1, 5, 25, -1, -1),
          intArrayOf(-1, 32, 30, -1, -1, -1)
      ) to 2
  ).forEachIndexed { index, (input, value) ->
    val output = snakesAndLadders.executeRecursive(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}