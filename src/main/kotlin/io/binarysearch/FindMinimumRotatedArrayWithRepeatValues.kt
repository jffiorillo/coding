package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1031/
class FindMinimumRotatedArrayWithRepeatValues {

  fun execute(input: IntArray): Int = when {
    input.isEmpty() -> -1
    input.size <= 2 -> input.min()!!
    else -> {
      var start = 0
      var end = input.size - 1
      while (start < end) {
        val pivot = start + (end - start) / 2
        when {
          input[pivot] < input[end] ->
            end = pivot
          input[pivot] > input[end] -> start = pivot + 1
          else -> end--
        }
      }
      input[start]
    }
  }
}

fun main() {
  val findMinimumRotatedArrayII = FindMinimumRotatedArrayWithRepeatValues()
  listOf(
//      intArrayOf(2, 2, 2, 0, 1),
//      intArrayOf(1, 3, 5),
//      intArrayOf(10, 1, 10, 10, 10),
//      intArrayOf(1, 1, 1),
      intArrayOf(10, 10, 10, 1)
  ).map { input ->
    val output = input.min()!!
    val result = findMinimumRotatedArrayII.execute(input)
    if (result == output) {
      println("${input.toList()} is valid")
    } else {
      println("${input.toList()} invalid output. Expected $output but $result")

    }
  }
}