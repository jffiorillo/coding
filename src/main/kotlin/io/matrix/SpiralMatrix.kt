package io.matrix

import io.utils.runTests

// https://leetcode.com/problems/spiral-matrix/solution/
class SpiralMatrix {

  fun execute(matrix: Array<IntArray>): List<Int> = mutableListOf<Int>().apply {
    var r0 = 0
    var r1 = matrix.size - 1
    var c0 = 0
    var c1 = (matrix.firstOrNull()?.size ?: 0) - 1
    while (r0 <= r1 && c0 <= c1) {
      (c0..c1).forEach {add(matrix[r0][it]) }
      (r0 + 1..r1).forEach { add(matrix[it][c1]) }
      if (r0 < r1 && c0 < c1) {
        (c1 - 1 downTo c0+1).forEach { add(matrix[r1][it]) }
        (r1 downTo r0+1).forEach { add(matrix[it][c0]) }
      }
      r0++
      r1--
      c0++
      c1--
    }
  }

}

fun main() {
  runTests(listOf(
      arrayOf(
          intArrayOf(1, 2, 3, 4, 5),
          intArrayOf(6, 7, 8, 9, 10),
          intArrayOf(11, 12, 13, 14, 15),
          intArrayOf(16, 17, 18, 19, 20))
    to listOf(1,2,3,4,5,10,15,20,19,18,17,16,11,6,7,8,9,14,13,12)
  )){(input,value) -> value to SpiralMatrix().execute(input) }
}