package io.array

// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
class FindPivotIndex {

  fun execute(input : IntArray): Int{
    val total = input.fold(0) { acc, value -> acc + value }
    var acc = 0
    input.forEachIndexed { index, value ->
      if ((total-value) == 2*acc) return index
      acc+=value
    }
    return -1
  }
}