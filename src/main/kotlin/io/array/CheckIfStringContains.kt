package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1161/
class CheckIfStringContains {

  fun execute(input: String, subString: String): Int = when {
    subString.isEmpty() -> 0
    subString.length > input.length -> -1
    else -> {
      var result = -1
      for (index in 0..input.length - subString.length) {
        if (input.areEquals(index, subString)) {
          result = index
          break
        }
      }
      result
    }
  }

  private fun String.areEquals(i: Int, other: String): Boolean {
    for (index in other.indices) {
      if (this[i + index] != other[index])
        return false
    }
    return true
  }
}

fun main() {

  runTests(listOf(
      Triple("hello", "ll", 2),
      Triple("aaaaa", "bba", -1)
  )) { (input, subString, value) -> value to CheckIfStringContains().execute(input, subString) }
}