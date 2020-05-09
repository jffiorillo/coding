package io.undefined

import kotlin.math.pow
import kotlin.math.sqrt

// https://leetcode.com/problems/k-closest-points-to-origin/
class ClosetsKPointsToZero {

  fun execute(input: Array<IntArray>, size: Int): Array<IntArray> {
    if (input.isEmpty()) return input
    input.sortBy { (x, y) -> sqrt(x.toDouble().pow(2) + y.toDouble().pow(2)) }
    val minSize = minOf(input.size, size)
    return Array(minSize) { IntArray(0) }.apply {
      (0 until minSize).forEach { this[it] = input[it] }
    }
  }
}