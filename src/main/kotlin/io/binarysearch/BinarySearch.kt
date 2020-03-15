package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/138/background/1038/
class BinarySearch {

  fun execute(input: IntArray, target: Int, begin: Int = 0, end: Int = input.size - 1): Int = when {
    begin > end -> -1
    begin == end -> if (input[begin] == target) begin else -1
    else -> {
      val pivot = end + begin ushr 1
      val value = input[pivot]
      when {
        value == target -> pivot
        value < target -> execute(input, target, pivot + 1, end)
        else -> execute(input, target, begin, pivot - 1)
      }
    }
  }
}

fun main() {
  val binarySearch = BinarySearch()
  println(binarySearch.execute(intArrayOf(-1,0,3,5,9,12),9))
}