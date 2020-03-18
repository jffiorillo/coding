package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/135/template-iii/944/
class SearchRange {
  fun execute(nums: IntArray, target: Int): IntArray = intArrayOf(find(nums, target) { it < target }, find(nums, target) { it <= target })

  private fun find(nums: IntArray, target: Int, evaluator: (Int) -> Boolean): Int {
    var index = -1
    var start = 0
    var end = nums.lastIndex

    while (start <= end) {
      val mid = start + (end - start) / 2

      if (nums[mid] == target) index = mid

      if (evaluator(nums[mid])) {
        start = mid + 1
      } else {
        end = mid - 1
      }
    }
    return index
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