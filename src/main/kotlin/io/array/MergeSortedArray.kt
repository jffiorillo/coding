package io.array

import io.utils.runTests

// https://leetcode.com/problems/merge-sorted-array/
class MergeSortedArray {

  // Time O(N+M) Space O(1)
  fun execute(input0: IntArray, size0: Int, input1: IntArray, size1: Int) {
    var index0 = size0 - 1
    var index1 = size1 - 1
    var index = input0.lastIndex
    while (index0 >= 0 && index1 >= 0) {
      when {
        input0[index0] > input1[index1] -> input0[index--] = input0[index0--]
        else -> input0[index--] = input1[index1--]
      }
    }
    while (index0 >= 0) input0[index--] = input0[index0--]
    while (index1 >= 0) input0[index--] = input1[index1--]
  }

  // Time O(N+M) Space O(size0+size1)
  fun execute1(input0: IntArray, size0: Int, input1: IntArray, size1: Int) {
    var index0 = 0
    var index1 = 0
    val temp = IntArray(size0 + size1)
    var index = 0
    while (index0 < size0 && index1 < size1) {
      when {
        input0[index0] < input1[index1] -> temp[index++] = input0[index0++]
        else -> temp[index++] = input1[index1++]
      }
    }
    while (index0 < size0) temp[index++] = input0[index0++]
    while (index1 < size1) temp[index++] = input1[index1++]
    temp.forEachIndexed { cIndex, value -> input0[cIndex] = value }
  }

}

@Suppress("ArrayInDataClass")
private data class Helper(val input0: IntArray, val size0: Int, val input1: IntArray, val size1: Int, val value: List<Int>)

fun main() {
  runTests(listOf(
      Helper(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(4, 5, 6), 3, listOf(1, 2, 3, 4, 5, 6))
  )) { (input0, size0, input1, size1, value) -> value to MergeSortedArray().execute(input0, size0, input1, size1).let { input0.toList() } }
}