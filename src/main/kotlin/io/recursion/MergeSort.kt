package io.recursion

// https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2944/
class MergeSort {


  fun executeRecursive(input: IntArray): List<Int> = when {
    input.isEmpty() -> emptyList()
    input.size == 1 -> listOf(input[0])
    else -> {
      val left = executeRecursive(input.copyOfRange(0, input.size / 2))
      val right = executeRecursive(input.copyOfRange(input.size / 2, input.size))
      merge(left, right)
    }
  }

  fun merge(left: List<Int>, right: List<Int>): List<Int> = when {
    left.isEmpty() -> right
    right.isEmpty() -> left
    else -> {
      mutableListOf<Int>().apply {
        var l = 0
        var r = 0
        while (l < left.size && r < right.size) {
          if (left[l] < right[r]) {
            add(left[l])
            l++
          } else {
            add(right[r])
            r++
          }
        }
        (l until left.size).map { add(left[it]) }
        (r until right.size).map { add(right[it]) }
      }
    }
  }


}