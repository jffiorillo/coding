package io.array

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3299/
class PerformStringShift {

  fun execute(input: String, shift: Array<IntArray>): String {
    val (left, right) = shift.fold(0 to 0) { (left, right), value ->
      when (value.first()) {
        0 -> left + value[1] to right
        else -> left to right + value[1]
      }
    }
    val finalMoves = left - right
    val startIndex = finalMoves.absoluteValue.rem(input.length)
    return when {
      finalMoves == 0 -> input
      finalMoves < 0 -> input.substring(input.length - startIndex) + input.substring(0, input.length - startIndex)
      finalMoves > 0 -> input.substring(startIndex) + input.substring(0, startIndex)
      else -> input
    }
  }
}

fun main() {
  runTests(listOf(
      Triple("abc", arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), "cab"),
      Triple("abc", arrayOf(intArrayOf(0, 3), intArrayOf(1, 2)), "bca"),
      Triple("mecsk", arrayOf(intArrayOf(1, 4), intArrayOf(0, 5), intArrayOf(0, 4), intArrayOf(1, 1), intArrayOf(1, 5)), "kmecs")
  )) { (input, shift, value) -> value to PerformStringShift().execute(input, shift) }
}