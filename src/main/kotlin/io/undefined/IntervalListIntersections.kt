package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/interval-list-intersections/
class IntervalListIntersections {

  fun execute(input0: Array<IntArray>, input1: Array<IntArray>): Array<IntArray> {
    var index0 = 0
    var index1 = 0
    val result = mutableListOf<IntArray>()
    while (index0 in input0.indices && index1 in input1.indices) {
      val (start0, end0) = input0[index0]
      val (start1, end1) = input1[index1]
      when {
        start0 in start1..end1 || start1 in start0..end0 -> {
          result.add(intArrayOf(maxOf(start0, start1), minOf(end0, end1)))
        }
      }
      if (end0 > end1) index1++ else index0++
    }
    return result.toTypedArray()
  }
}

fun main() {
  runTests(listOf(
      Triple(arrayOf(intArrayOf(0, 2), intArrayOf(5, 10), intArrayOf(13, 23), intArrayOf(24, 25)),
          arrayOf(intArrayOf(1, 5), intArrayOf(8, 12), intArrayOf(15, 24), intArrayOf(25, 26)),
          listOf(listOf(1, 2), listOf(5, 5), listOf(8, 10), listOf(15, 23), listOf(24, 24), listOf(25, 25)))
  )) { (input0, input1, value) -> value to IntervalListIntersections().execute(input0, input1).map { it.toList() } }
}

