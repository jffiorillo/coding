@file:Suppress("unused")

package io.array

// https://leetcode.com/problems/monotonic-array/
class MonotonicArray {

  fun execute(input: IntArray): Boolean {
    if (input.isEmpty()) return true
    var isMonotonic = true
    var index = 0
    while (index in 0 until input.lastIndex) {
      if (input[index + 1] < input[index]) {
        isMonotonic = false
        break
      }
      index++
    }
    if (isMonotonic) return true
    else if (index > 0 && input[index - 1] != input[index]) return false
    isMonotonic = true
    for (index in 0 until input.lastIndex) {
      if (input[index + 1] > input[index]) {
        isMonotonic = false
        break
      }
    }
    return isMonotonic
  }
}
