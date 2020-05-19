@file:Suppress("unused")

package io.matrix


open class Relation {
  fun knows(a: Int, b: Int): Boolean = true
}

// https://leetcode.com/problems/find-the-celebrity/
class FindCelebrity : Relation() {

  fun execute(input: Int): Int {
    val memory = Array(input) { row -> IntArray(input) { col -> if (col == row) 0 else 0 } }
    val possibleCelebrity = findPossibleCelebrity(memory)
    if (possibleCelebrity in memory.indices)
      for (col in memory.indices) {
        if (memory[possibleCelebrity][col] != 1 && knows(possibleCelebrity, col))
          return -1
      }
    return possibleCelebrity
  }


  // Find somebody that doesn't know nobody
  private fun findPossibleCelebrity(memory: Array<IntArray>): Int {
    for (row in memory.indices) {
      if (memory[row].all { it == 0 }) {
        var found = false
        for (col in memory.indices) {
          if (row == col) continue
          if (knows(row, col)) {
            memory[row][col] = 1
            found = true
            break
          }
        }
        if (!found) return row
      }
    }
    return -1
  }
}