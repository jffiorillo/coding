package io.array

import java.security.InvalidParameterException

// https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
class ShortestUnsortedSubArray {

  fun execute(input: IntArray): Int =
      minElementInBrokenArray(input)?.let { minValueInBrokenArray ->
        val maxValueInBrokenArray = maxElementInBrokenArray(input)
        val left = indexOfCorrectPosition(input, minValueInBrokenArray)
        val right = indexOfMaxCorrectPosition(input, maxValueInBrokenArray)
        right - left + 1
      } ?: 0

  private fun indexOfCorrectPosition(input: IntArray, minValue: Int) =
      input.foldIndexed(null as Int?) { index, acc, value -> acc ?: if (minValue < value) index else null }!!

  private fun indexOfMaxCorrectPosition(input: IntArray, maxValue: Int) =
      (input.size - 1 downTo 1).fold(null as Int?) { acc, index -> acc ?: if (maxValue > input[index]) index else null }!!

  private fun minElementInBrokenArray(input: IntArray): Int? {
    for (x in 0 until input.size - 1) {
      if (input[x + 1] < input[x]) {
        return input.slice(x until input.size).minOrNull()!!
      }
    }
    return null
  }

  private fun maxElementInBrokenArray(input: IntArray): Int {
    for (x in input.size - 1 downTo 1) {
      if (input[x] < input[x - 1]) {
        return input.slice(0 until x).maxOrNull()!!
      }
    }
    throw InvalidParameterException("This should never happens, broken array not found")
  }
}