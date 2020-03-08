package io.queue

import java.util.*

//https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1388/
class FindNearestZero {

  private val defaultValue = Int.MAX_VALUE

  fun execute(input: Array<IntArray>): Array<IntArray> = Array(input.size) { IntArray(input[0].size) }.apply {
    for (row in this) Arrays.fill(row, defaultValue)
    input.mapIndexed { x, row ->
      row.mapIndexed { y, element ->
        when {
          element == 0 -> this[x][y] = 0
          this[x][y] == defaultValue -> {
            var stack = listOf(CoordinatesLinkedList(Coordinates(x, y)))
            var steps = 0
            var found = false
            while (!found) {
              stack = stack.flatMap { current ->
                when {
                  input[current.coordinates.i][current.coordinates.j] == 0 -> {
                    this[x][y] = steps
                    found = true
                    updateParent(this, current)
                    emptyList()
                  }
                  else -> current.coordinates.generateChild(input.size, input[0].size).map { CoordinatesLinkedList(it, current) }
                }
              }
              steps++
            }
          }
        }
      }
    }
  }

  private fun updateParent(output: Array<IntArray>, coordinatesLinkedList: CoordinatesLinkedList) =
      generateSequence(coordinatesLinkedList to 0) { seed -> seed.first.father?.let { it to (seed.second + 1) } }
          .map { (coordinates, value) ->
            if (output[coordinates.coordinates.i][coordinates.coordinates.j] == defaultValue)
              output[coordinates.coordinates.i][coordinates.coordinates.j] = value
          }


  data class CoordinatesLinkedList(val coordinates: Coordinates, val father: CoordinatesLinkedList? = null)
}

fun main() {
  val findNearestZero = FindNearestZero()
  listOf(
      arrayOf(
          intArrayOf(0, 0, 0),
          intArrayOf(0, 1, 0),
          intArrayOf(0, 0, 0)
      ) to
          arrayOf(
              intArrayOf(0, 0, 0),
              intArrayOf(0, 1, 0),
              intArrayOf(0, 0, 0)),
      arrayOf(
          intArrayOf(0, 0, 0),
          intArrayOf(0, 1, 0),
          intArrayOf(1, 1, 1)
      ) to
          arrayOf(
              intArrayOf(0, 0, 0),
              intArrayOf(0, 1, 0),
              intArrayOf(1, 2, 1)
          ),
      arrayOf(
          intArrayOf(0, 1, 0, 1, 1),
          intArrayOf(1, 1, 0, 0, 1),
          intArrayOf(0, 0, 0, 1, 0),
          intArrayOf(1, 0, 1, 1, 1),
          intArrayOf(1, 0, 0, 0, 1)
      ) to
          arrayOf(
              intArrayOf(0, 1, 0, 1, 2),
              intArrayOf(1, 1, 0, 0, 1),
              intArrayOf(0, 0, 0, 1, 0),
              intArrayOf(1, 0, 1, 1, 1),
              intArrayOf(1, 0, 0, 0, 1)
          )
  ).mapIndexed { index, (input, output) ->
    val execute = findNearestZero.execute(input)
    val isValid = execute.indices.fold(true) { iAcc, i ->
      iAcc && execute.first().indices.fold(true) { jAcc, j -> jAcc && execute[i][j] == output[i][j] }
    }
    println("$index is ${if (isValid) "valid" else "invalid"}")
  }

}