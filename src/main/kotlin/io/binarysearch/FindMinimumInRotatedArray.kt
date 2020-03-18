package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/126/template-ii/949/
class FindMinimumInRotatedArray {

  fun execute(input: IntArray): Int {
    var start = 0
    var end = input.size - 1
    while (start < end) {
      val pivot = start + (end - start) / 2
      when {
        input[pivot] < input[0] -> end = pivot
        else -> start = pivot + 1
      }
    }
    return if (input.isNotEmpty()) minOf(input[start], input.first()) else -1
  }
}

fun main() {
  val findMinimumInRotatedArray = FindMinimumInRotatedArray()
  listOf(
//      intArrayOf(4, 5, 6, 7, 0, 1, 2),
//      intArrayOf(1, 2),
//      intArrayOf(3, 4, 5, 1, 2),
      intArrayOf(3, 1, 2)
  ).map { println(findMinimumInRotatedArray.execute(it)) }
}