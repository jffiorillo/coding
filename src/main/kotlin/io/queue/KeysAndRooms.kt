package io.queue

import java.util.*

// https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1391/
class KeysAndRooms {

  fun execute(rooms: List<List<Int>>): Boolean {

    val stack = Stack<Int>()
    val visited = BooleanArray(rooms.size)
    stack.push(0)
    while (stack.isNotEmpty()) {
      stack.pop().let { current ->
        visited[current] = true
        rooms[current].filter { !visited[it] }.map { stack.push(it) }
      }
    }
    return visited.allVisited()
  }

  private fun BooleanArray.allVisited() = this.reduce { acc, b -> acc && b }


  fun executeRecursive(rooms: List<List<Int>>, current: Int = 0, visited: BooleanArray = BooleanArray(rooms.size)): Boolean {
    visited[current] = true
    return when {
      rooms[current].isEmpty() || rooms[current].none { !visited[it] } -> visited.allVisited()
      else -> rooms[current].filter { !visited[it] }.fold(false) { acc, i -> acc || executeRecursive(rooms, i, visited) }
    }
  }

}


fun main() {
  val keysAndRooms = KeysAndRooms()
  listOf(
      listOf(listOf(1), listOf(2), listOf(3), emptyList()) to true,
      listOf(listOf(2, 3), emptyList(), listOf(2), listOf(1, 3, 1)) to true,
      listOf(listOf(1,3),listOf(3,0,1),listOf(2),listOf(0)) to false
  )
      .mapIndexed { index, (rooms, output) ->
        println("$index isValid: ${keysAndRooms.executeRecursive(rooms) == output}")
      }
}




