package io.slidingwindow

import io.utils.runTests
import java.util.*


class MaximumSumSubarraySumEqualsK {

  fun execute(nums: IntArray, k: Int): Int {
    var sum = 0
    var max = 0
    val map = HashMap<Int, Int>()
    nums.forEachIndexed { index, value ->
      sum += value
      when {
        sum == k -> max = index + 1
        map.containsKey(sum - k) ->
          max = maxOf(max, index - map.getValue(sum - k))
      }
      if (!map.containsKey(sum)) map[sum] = index
    }
    return max
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(1, -1, 5, -2, 3), 2, 3),
      Triple(intArrayOf(1, -1, 5, -2, 3), 5, 4)
  )) { (input, k, value) -> value to MaximumSumSubarraySumEqualsK().execute(input, k) }
}