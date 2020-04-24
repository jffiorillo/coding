package io.math

import io.utils.runTests


// Given a number n, generate all the permutations  list of 1 to n
// https://www.youtube.com/watch?v=V7hHupttzVk
class PermutationGenerator {

  fun execute(input: Int): Array<IntArray> = Array(input.factorial()) { IntArray(input) }.apply {
    (1..input).forEach { first()[it - 1] = it }
    var index = 1
    while (index < input.factorial()) {
      var decrease = input - 2
      (0 until input).forEach { this[index][it] = this[index - 1][it] }
      val current = this[index]
      while (decrease in first().indices && current[decrease] > current[decrease + 1]) {
        if (decrease == 0) {
          break
        }
        decrease--
      }

      var larger = input - 1
      while (current[larger] < current[decrease]) larger--
      current.swap(decrease, larger)
      var right = input - 1
      var left = decrease + 1
      while (left < right) {
        current.swap(left, right)
        left++
        right--
      }
      index++
    }
  }

  private fun IntArray.swap(index0: Int, index1: Int) = this[index0].let { temp ->
    this[index0] = this[index1]
    this[index1] = temp
  }

  private fun Int.factorial(): Int = when (this) {
    in 2 until Int.MAX_VALUE -> this * (this - 1).factorial()
    else -> 1
  }
}

fun main() {
  runTests(listOf(
      2 to listOf(listOf(1, 2), listOf(2, 1)),
      3 to listOf(listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3), listOf(2, 3, 1), listOf(3, 1, 2), listOf(3, 2, 1))
  )) { (input, value) -> value to PermutationGenerator().execute(input).map { it.toList() }.toList() }
}