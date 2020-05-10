package io.array

import io.utils.runTests

// https://leetcode.com/problems/kth-largest-element-in-an-array/
class KthLargestElementInArray {

  // https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60312/AC-Clean-QuickSelect-Java-solution-avg.-O(n)-time
  fun execute(input: IntArray,
              kth: Int,
              low: Int = 0,
              high: Int = input.lastIndex): Int {
    var pivot = low

    // use quick sort's idea
    // put nums that are <= pivot to the left
    // put nums that are  > pivot to the right
    for (index in low until high) {
      if (input[index] <= input[high]) {
        input.swap(pivot++, index)
      }
    }
    input.swap(pivot, high)

    // count the nums that are > pivot from high
    val count = high - pivot + 1
    return when {
      // pivot is the one!
      count == kth -> input[pivot]
      // pivot is too small, so it must be on the right
      count > kth -> execute(input, kth, pivot + 1, high)
      // pivot is too big, so it must be on the left
      else -> execute(input, kth - count, low, pivot - 1)
    }
  }
}

private fun IntArray.swap(pivot: Int, j: Int) {
  this[pivot].let {
    this[pivot] = this[j]
    this[j] = it
  }
}

fun main() {
  runTests(listOf(
//      Triple(intArrayOf(1), 1, 1),
      Triple(intArrayOf(3, 2, 1, 5, 6, 4), 2, 5)
  )) { (input, kth, value) -> value to KthLargestElementInArray().execute(input, kth) }
}