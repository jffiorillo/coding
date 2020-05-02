package io.undefined

import io.utils.runTests


// https://leetcode.com/problems/friend-circles/
class FriendCircles {

  fun execute(input: Array<IntArray>): Int {
    val friends = Array(input.size) { mutableListOf<Int>() }
    input.indices.forEach { row ->
      (row + 1 until input.size).forEach { col ->
        if (input[row][col] == 1) {
          friends[col].add(row)
          friends[row].add(col)
        }
      }
    }

    val visited = mutableSetOf<Int>()
    var steps = 0
    for (index in friends.indices) {
      if (visited.contains(index)) continue
      friends.visitFriends(index, visited)
      steps++
    }
    return steps
  }

  private fun Array<MutableList<Int>>.visitFriends(index: Int, visited: MutableSet<Int>) {
    visited.add(index)
    var stack: List<Int> = this[index]
    while (stack.isNotEmpty()) {
      stack = stack.flatMap {
        if (visited.contains(it)) emptyList<Int>() else {
          visited.add(it)
          this[it]
        }
      }
    }
  }
}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(1, 1, 0, 0),
          intArrayOf(1, 1, 1, 0),
          intArrayOf(0, 1, 1, 1),
          intArrayOf(0, 0, 1, 1)
      ) to 1,
      arrayOf(
          intArrayOf(1, 0, 0, 1),
          intArrayOf(0, 1, 1, 0),
          intArrayOf(0, 1, 1, 1),
          intArrayOf(1, 0, 1, 1)
      ) to 1
  )) { (input, value) -> value to FriendCircles().execute(input) }
}