package io.array

// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1147/
class LargerNumberTwiceAsOther {

  fun execute(input: IntArray): Int {
    var maxValue = 0
    var result = -1
    input.forEachIndexed { index, value ->
      if (value > maxValue) {
        result = index
        maxValue = value
      }
    }
    var i = 0
    while (i < input.size) {
      if (i != result && input[i] * 2 > maxValue) return -1
      i++
    }
    return result
  }
}