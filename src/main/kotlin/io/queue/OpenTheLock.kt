package io.queue

import java.util.*
import kotlin.collections.HashSet


//https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1375/
class OpenTheLock {
  private val initial = listOf(0, 0, 0, 0)
  private val error = -1

  fun execute(deadEnds: Array<String>, goal: String): Int {
    val start = initial
    val target = goal.lockArrayListOfInt()
    val avoid = deadEnds.map { it.lockArrayListOfInt() }.toSet()

    if (start in avoid)
      return error

    var distance = 0
    var toVisit = listOf(start)
    val visited = HashSet<List<Int>>()

    while (toVisit.isNotEmpty()) {
      val nextToVisit = mutableListOf<List<Int>>()

      for (node in toVisit) {
        if (node == target)
          return distance

        for (n in retrieveNeighbours(node)) {
          if (n !in visited && n !in avoid) {
            visited.add(n)
            nextToVisit.add(n)
          }
        }
      }

      toVisit = nextToVisit
      distance += 1
    }

    return error
  }

  private fun retrieveNeighbours(current: List<Int>) = arrayListOf<List<Int>>().apply {
    (0..3).map { i ->
      val next = ArrayList(current)
      next[i] = (current[i] + 1) % 10
      add(ArrayList(next))
      next[i] = (current[i] + 9) % 10
      add(next)
    }
  }
  private fun String.lockArrayListOfInt() = (0..3).map { Character.getNumericValue(this[it]) }
}


fun main() {
  val openTheLock = OpenTheLock()
  listOf(
      Help(deadEnds = arrayOf("0201", "0101", "0102", "1212", "2002"), target = "0202", output = 6),
      Help(deadEnds = arrayOf("8888"), target = "0009", output = 1),
      Help(deadEnds = arrayOf("8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"), target = "8888", output = -1)
  ).mapIndexed { index, item ->
    val output = openTheLock.execute(deadEnds = item.deadEnds, goal = item.target)
    if (output != item.output) {
      println("error with $item wrong output $output")
    } else println("done with $index")
  }
}

private data class Help(val deadEnds: Array<String>, val target: String, val output: Int)