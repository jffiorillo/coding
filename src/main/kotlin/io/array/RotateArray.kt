package io.array

import io.utils.runTests


// https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1182/
class RotateArray {

  fun executeInPlace(input: IntArray, k: Int): List<Int> {
    val windowsSlices = k.rem(input.size)
    var start = 0
    var count = 0
    while (count < input.size) {
      var current = start
      var pre = input[current]
      do {
        val nextIndex = (current + windowsSlices).rem(input.size)
        val temp = input[nextIndex]
        input[nextIndex] = pre
        pre = temp
        count++
        current = nextIndex
      } while (current != start)
      start++
    }
    return input.toList()
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(0, 1, 2, 3, 4, 5, 6, 7), 2, listOf(6, 7, 0, 1, 2, 3, 4, 5)),
      Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7), 2, listOf(6, 7, 1, 2, 3, 4, 5)),
      Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3, listOf(5, 6, 7, 1, 2, 3, 4)),
      Triple(intArrayOf(1, 2), 3, listOf(2, 1))

  )) { (input, k, value) -> (value to RotateArray().executeInPlace(input.clone(), k)) }
}