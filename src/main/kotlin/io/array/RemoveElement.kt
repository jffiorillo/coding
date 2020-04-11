package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1151/
class RemoveElement {

  fun execute(input: IntArray, value: Int): Int {
    var newIndex = 0
    input.forEach {
      if (value != it) {
        input[newIndex] = it
        newIndex++
      }
    }
    return newIndex
  }
}


fun main() {
  runTests(listOf(
      Triple(intArrayOf(2, 3, 2, 3), 3, 2)
  )) { (input, target, value) -> value to RemoveElement().execute(input, target) }
}