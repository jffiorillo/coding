package io.dp

import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/subsets-ii/
class PowerSetsII {

  fun execute(input: IntArray, size: Int = 0): List<List<Int>> = when (size) {
    !in input.indices -> listOf(listOf())
    else -> {
      execute(input, size + 1).let { previousSubsets ->
        previousSubsets +
            previousSubsets.map { it + input[size] } +
            listOf(listOf(input[size]))
      }.distinctBy { it.sorted() }.also { item -> item.sortedBy { it.size } }
    }
  }

  fun countSubsets(nums: IntArray, k: Int): Int {
    Arrays.sort(nums)
    var count = 0
    var lo = 0
    var hi = nums.size - 1
    while (lo <= hi) {
      if (nums[lo] + nums[hi] > k) {
        hi--
      } else {
        count += 1 shl (hi - lo)
        lo++
      }
    }
    return count
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(1, 2, 2) to listOf(emptyList(), listOf(2), listOf(1), listOf(1, 2, 2), listOf(2, 2), listOf(1, 2), emptyList()),
      intArrayOf(4, 4, 4, 1, 4) to listOf(emptyList(), listOf(1),
          listOf(1, 4), listOf(1, 4, 4), listOf(1, 4, 4, 4), listOf(1, 4, 4, 4, 4), listOf(4), listOf(4, 4), listOf(4, 4, 4), listOf(4, 4, 4, 4))
  ),evaluation = {value, output ->
    value.map { it.toSet() }.toSet() == output.map { it.toSet() }.toSet()
  }) { (input, value) -> value to PowerSetsII().execute(input) }
}
