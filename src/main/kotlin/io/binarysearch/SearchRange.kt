package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/135/template-iii/944/
class SearchRange {
  fun execute(nums: IntArray, target: Int): IntArray = when {
    nums.size == 0 -> intArrayOf(-1, -1)
    else -> {
      var start = 0
      var end = nums.size
      var index = -1
      while (start <= end && start < nums.size) {
        val pivot = start + (end - start) / 2
        if (nums[pivot] == target) {
          index = pivot
          break
        } else if (nums[pivot] < target) {
          start = pivot + 1
        } else end = pivot - 1
      }
      if (index == -1)
        intArrayOf(-1, -1)
      else intArrayOf(searchNumbers(nums, right = index, condition = { value -> value == target }),
          searchNumbersRight(nums, target, index))
    }
  }

  fun searchNumbers(nums: IntArray, left: Int = 0, right: Int = nums.size - 1, condition: (Int) -> Boolean): Int {
    var start = left
    var end = right
    while (start < end) {
      val pivot = start + (end - start) / 2
      if (condition(nums[pivot])) end = pivot
      else start = pivot + 1
    }
    return start
  }

  fun searchNumbersRight(nums: IntArray, target: Int, left: Int): Int {
    var start = left
    var end = nums.size - 1
    while (start < end) {
      val pivot = 1 + start + (end - start) / 2
      if (nums[pivot] != target) end = pivot - 1
      else start = pivot
    }
    return start
  }
}

fun main() {
  val searchRange = SearchRange()
  listOf(
      Triple(intArrayOf(5, 7, 7, 8, 8, 10), 8, listOf(3, 4)),
      Triple(intArrayOf(5, 7, 7, 8, 8, 10), 6, listOf(-1, -1)),
      Triple(intArrayOf(1, 2, 3, 4, 4, 4, 5, 5, 5, 7, 7, 7, 7, 7, 7, 8, 8, 10, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 13, 14, 15, 15, 15, 15), 7, listOf(9, 14)),
      Triple(intArrayOf(1, 4), 4, listOf(1, 1)),
      Triple(intArrayOf(1), 1, listOf(0, 0)),
      Triple(intArrayOf(1, 3), 1, listOf(0, 0))
  ).map { (input, target, output) ->
    val solution = searchRange.execute(input, target).toList()
    println("$solution is valid ${solution == output}")
  }
}