package io.numbers

import kotlin.math.pow


// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3284/
class HappyNumbers {

  fun execute(n: Int): Boolean {
    var current = n
    val visited = hashSetOf<Int>()
    while (current > 1) {
      if (visited.contains(current)) break
      visited.add(current)
      current = current.squareSumOfNumber()
    }
    return current == 1
  }
}

private fun Int.squareSumOfNumber() = (intArrayOf(0)+this).reduce { acc, value ->  acc + value*value }

fun main() {
  val happyNumbers = HappyNumbers()
  listOf(
      19 to true,
      54 to false,
      2 to false
  ).forEachIndexed { index, (input, value) ->
    val output = happyNumbers.execute(input)
    if (output == value)
      println("index $index output $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}