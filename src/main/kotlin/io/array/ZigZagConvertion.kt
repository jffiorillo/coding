package io.array

import io.utils.runTests

// https://leetcode.com/problems/zigzag-conversion/
class ZigZagConversation {

  fun execute(input: String, nRows: Int): String? {
    val result = Array(nRows) { StringBuffer() }
    var index = 0
    while (index < input.length) {
      // vertically down
      for (iIndex in 0 until nRows) {
        if (index == input.length) break
        result[iIndex].append(input[index++])
      }
      // obliquely up
      for (iIndex in nRows - 2 downTo 1) {
        if (index == input.length) break
        result[iIndex].append(input[index++])
      }
    }
    return  result.reduce { acc, value -> acc.append(value) }.toString()
  }
}

fun main() {
  runTests(listOf(
      Triple("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),
      Triple("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),
      Triple("PAYPALISHIRING", 8, "PINALSIGYAHRPI")
  )) { (input, nRows, value) -> value to ZigZagConversation().execute(input, nRows) }
}