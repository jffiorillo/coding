package io.array

import io.utils.runTests


// https://leetcode.com/problems/wiggle-sort/
class WiggleSort {

  fun execute(input: IntArray) {
    if (input.size <= 1) return
    var index = 0
    while (index < input.size) {
      when {
        index.rem(2) == 0 && index + 1 < input.size && input[index + 1] < input[index] -> input.swap(index, index + 1)
        index.rem(2) == 1 && index + 1 < input.size && input[index + 1] > input[index] -> input.swap(index, index + 1)
      }
      index++
    }
  }

  private fun IntArray.swap(index: Int, index1: Int) {
    val temp = this[index]
    this[index] = this[index1]
    this[index1] = temp
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(3, 5, 2, 1, 6, 4) to listOf(3, 5, 1, 6, 2, 4)
  )) { (input, value) -> value to input.let { WiggleSort().execute(it); it.toList() } }
}