package io.array

import kotlin.math.absoluteValue

// https://leetcode.com/problems/squares-of-a-sorted-array/
class SquareOfSortedArray {

  fun execute(A: IntArray): IntArray {
    var input = A
    if (input.any { it < 0 }) {
      input = input.sortedBy { it.absoluteValue }.toIntArray()
    }
    input.forEachIndexed { index, value -> input[index] = value * value }
    return input
  }

}