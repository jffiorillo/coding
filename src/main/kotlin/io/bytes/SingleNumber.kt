package io.bytes

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3283/
class SingleNumber {

  fun execute(input: IntArray) = input.reduce { acc, value -> acc xor value }
}

fun main() {
  listOf(
      intArrayOf(2, 1, 2, 3, 3) to 1
  ).forEachIndexed { index, (input, value) ->
    val output = SingleNumber().execute(input)
    if (output == value)
      println("index $index output $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}