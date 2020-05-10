package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/add-strings/
class AddStrings {

  fun execute(input0: String, input1: String): String {
    var index0 = input0.lastIndex
    var index1 = input1.lastIndex
    var accum = 0
    val result = StringBuilder()
    while (index0 >= 0 || index1 >= 0 || accum > 0) {
      val num = input0.toNum(index0) + input1.toNum(index1) + accum
      result.append(num.rem(10))
      accum = num / 10
      index0--
      index1--
    }
    return result.reverse().toString()
  }
}

private fun String.toNum(index:Int) = if (index in indices) this[index] - '0' else 0

fun main() {
  runTests(listOf(
      Triple("532", "5010", "5542"),
      Triple("", "5010", "5010")
  )) { (input0, input1, value) -> value to AddStrings().execute(input0, input1) }
}