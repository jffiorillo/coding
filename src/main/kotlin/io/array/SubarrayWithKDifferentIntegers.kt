package io.array

import io.utils.runTests

// https://leetcode.com/problems/subarrays-with-k-different-integers/
class SubarrayWithKDifferentIntegers {


  fun execute(input: IntArray, k: Int): Int {
    var res = 0
    var prefix = 0
    val m = IntArray(input.size + 1)
    var i = 0
    var j = 0
    var cnt = 0
    while (i < input.size) {
      if (m[input[i]]++ == 0) ++cnt
      if (cnt > k) {
        --m[input[j++]]
        --cnt
        prefix = 0
      }
      while (m[input[j]] > 1) {
        ++prefix
        --m[input[j++]]
      }
      if (cnt == k) res += prefix + 1
      ++i
    }
    return res
  }
}

fun main() {

  runTests(listOf(
      Triple(intArrayOf(1, 2, 1, 2, 3), 2, 7)
//      Triple(intArrayOf(1, 2, 1, 3, 4), 3, 3),
//      Triple(intArrayOf(), 10, 0),
//      Triple(intArrayOf(1), 2, 0),
//      Triple(intArrayOf(1, 2), 1, 2)
  )) { (input, k, value) -> value to SubarrayWithKDifferentIntegers().execute(input, k) }
}