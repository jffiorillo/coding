package io.matrix

import io.utils.runTests


// https://leetcode.com/problems/sparse-matrix-multiplication/
class SpareMatrixMultiplication {

  fun execute(input0: Array<IntArray>, input1: Array<IntArray>): Array<IntArray> =
      Array(input0.size) { IntArray(input1.firstOrNull()?.size ?: 0) }.apply {
        for (row in input0.indices) {
          for (secondCol in input1.firstOrNull()?.indices ?: 0 until 0) {
            for (col in input1.indices) {
              this[row][secondCol] += input0[row][col] * input1[col][secondCol]
            }
          }
        }
      }
}

fun main() {
  runTests(listOf(
      Triple(arrayOf(intArrayOf(1, 0, 0), intArrayOf(-1, 0, 3)),
          arrayOf(intArrayOf(7, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 1)),
          listOf(listOf(7, 0, 0), listOf(-7, 0, 3))),
      Triple(arrayOf(intArrayOf(1, -5)),
          arrayOf(intArrayOf(12), intArrayOf(-1)),
          listOf(listOf(17)))

  )) { (input0, input1, value) -> value to SpareMatrixMultiplication().execute(input0, input1).map { it.toList() } }
}