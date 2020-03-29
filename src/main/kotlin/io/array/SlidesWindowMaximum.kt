package io.array

// https://leetcode.com/problems/sliding-window-maximum/
class SlidesWindowMaximum {

  fun execute(nums: IntArray, k: Int): IntArray = when (k) {
    1 -> nums
    else -> {
      val result = mutableListOf<Int>()
      for (i in 0..nums.size - k) {
        var inner = i + 1
        var currentMax = nums[inner-1]
        while (inner < k + i) {
          currentMax = maxOf(currentMax, nums[inner])
          inner++
        }
        result.add(currentMax)
      }
      result.toIntArray()
    }
  }
}

fun main() {
  val slidesWindowMaximum = SlidesWindowMaximum()
  listOf(
      Triple(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3, listOf(3, 3, 5, 5, 6, 7))
  ).forEachIndexed { index, (input, k, value) ->
    val output = slidesWindowMaximum.execute(input, k).toList()
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}