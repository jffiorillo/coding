package io.numbers

import kotlin.math.pow


// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3284/
class HappyNumbers {

  fun execute(n: Int): Boolean {
    var current = n
    val visited = hashSetOf<Int>()
    while (current > 1) {
      var temp = current
      if (visited.contains(current)) break
      visited.add(current)
      current = 0
      while (temp > 0) {
        current += temp.rem(10).toDouble().pow(2).toInt()
        temp /= 10
      }
    }
    return current == 1
  }
}

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