@file:Suppress("unused")

package io.math

// https://leetcode.com/problems/sort-transformed-array/
class SortTransformedArray {

  // https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution
  fun execute(input: IntArray, a: Int, b: Int, c: Int) = IntArray(input.size).apply {
    var start = 0
    var end = input.lastIndex
    var index = if (a >= 0) input.lastIndex else 0
    while (start <= end) {
      when {
        a >= 0 -> this[index--] = if (quad(input[start], a, b, c) >= quad(input[end], a, b, c)) quad(input[start++], a, b, c) else quad(input[end--], a, b, c)
        else -> this[index++] = if (quad(input[start], a, b, c) >= quad(input[end], a, b, c)) quad(input[end--], a, b, c) else quad(input[start++], a, b, c)
      }
    }
  }

  private fun quad(x: Int, a: Int, b: Int, c: Int): Int = a * x * x + b * x + c
}