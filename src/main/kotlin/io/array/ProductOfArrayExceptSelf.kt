package io.array

import io.utils.runTests

// https://leetcode.com/problems/product-of-array-except-self/
class ProductOfArrayExceptSelf {

  fun execute(input: IntArray): IntArray {
    val result = IntArray(input.size) { 1 }
    for (index in 1 until input.size) {
      result[index] = result[index - 1] * input[index - 1]
    }
    var acc = 1
    for (index in input.size - 1 downTo 0) {
      result[index] = result[index] * acc
      acc *= input[index]
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(1, 2, 3, 4) to listOf(24, 12, 8, 6)
  )) { (input, value) -> value to ProductOfArrayExceptSelf().execute(input).toList() }
}