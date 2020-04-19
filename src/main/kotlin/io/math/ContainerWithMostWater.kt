package io.math

import io.utils.runTests

// https://leetcode.com/problems/container-with-most-water/
class ContainerWithMostWater {

  fun execute(input: IntArray): Int {

    var start = 0
    var end = input.size - 1
    var max = 0
    while (start < end) {
      val first = input[start]
      val last = input[end]
      val size = (end - start) * minOf(first, last)
      max = maxOf(size, max)
      if (first < last) {
        start++
      } else {
        end--
      }
    }
    return max
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7) to 49
  )) { (input, value) -> value to ContainerWithMostWater().execute(input) }
}