package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/trapping-rain-water/
class TrappingRainWater {

  // https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
  fun execute(heights: IntArray): Int {
    var start = 0
    var end = heights.lastIndex
    var max = 0
    var leftmax = 0
    var rightmax = 0
    while (start <= end) {
      leftmax = maxOf(leftmax, heights[start])
      rightmax = maxOf(rightmax, heights[end])
      when {
        leftmax < rightmax -> {
          max += leftmax - heights[start] // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
          start++
        }
        else -> {
          max += rightmax - heights[end]
          end--
        }
      }
    }
    return max
  }

  fun execute1(heights: IntArray): Int {
    val result = mutableSetOf<Pair<Int, Int>>()
    val towers = mutableListOf<Pair<Int, Int>>()

    loop@ for (index in heights.indices) {
      val value = heights[index]
      if (index == 0) {
        if (value > 0) towers.add(index to value)
        continue
      }
      if (heights[index - 1] < value) {
        if (towers.isNotEmpty()) {
          addValues(heights, towers, value, index, result)
          removeInvalidTowers(towers, value)
        }
        towers.add(index to value)
      }
    }

    return result.size
  }

  private fun addValues(heights: IntArray, towers: MutableList<Pair<Int, Int>>, currentValue: Int, currentIndex: Int,
                        result: MutableSet<Pair<Int, Int>>) {
    for (pair in towers.reversed()) {
      generateAllValues(heights, pair.first, currentIndex, minOf(currentValue, pair.second), result)
      if (pair.second >= currentValue) {
        break
      }
    }
  }

  private fun generateAllValues(heights: IntArray, index0: Int, index1: Int, maxHeight: Int, result: MutableSet<Pair<Int, Int>>) {
    (index0 + 1 until index1).forEach { row ->
      val minHeight = heights[row]
      (minHeight until maxHeight).forEach { col -> result.add(row to col) }
    }
  }
  private fun removeInvalidTowers(towers: MutableList<Pair<Int, Int>>, value: Int) {
    val indexToRemove = mutableListOf<Int>()
    for (index in towers.lastIndex downTo 0) {
      val pair = towers[index]
      if (pair.second >= value) {
        break
      }
      indexToRemove.add(index)
    }
    indexToRemove.forEach { towers.removeAt(it) }
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1) to 6,
      intArrayOf(2, 0, 2) to 2,
      intArrayOf(0, 3, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1) to 12,
      intArrayOf(4,1,3,0,1,0,2,7) to 17
  )) { (input, value) -> value to TrappingRainWater().execute(input) }
}