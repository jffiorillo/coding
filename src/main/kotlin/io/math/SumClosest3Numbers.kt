package io.math

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/3sum-closest/
class SumClosest3Numbers {

  fun execute(input: IntArray, target: Int): Int {
    input.sort()
    var closest: Int? = null
    for (index in 0 until input.size - 2) {
      val value = input[index]
      val sum = target - value
      var low = index + 1
      var high = input.size - 1
      while (low < high) {
        val extra = input[low] + input[high]
        if (closest == null || (target - (value + extra)).absoluteValue < (target - closest).absoluteValue) {
          closest = value + extra
        }
        when {
          sum == extra -> {
            return target
          }
          extra < sum -> low++
          else -> high--
        }
      }
    }
    return closest ?: 0
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(0, -5, 1, -3, 8, 3, 9), -3, -2),
      Triple(intArrayOf(0, -5, 1, -3, 8, 3, 9), 0, 0),
      Triple(intArrayOf(0, -5, 1, -3, 8, 3, 9), -6, -5),
      Triple(intArrayOf(-3, -2, -5, 3, -4), -1, -2)
  )) { (input, target, value) -> value to SumClosest3Numbers().execute(input, target) }
}