package io.combination

import io.utils.runTests

// https://leetcode.com/problems/combination-sum-ii/
class CombinationSumII {

  fun execute(input: IntArray, target: Int): List<List<Int>> {
    val validValues = input.filter { it <= target }
    if (validValues.isEmpty()) return emptyList()
    val result = mutableSetOf<List<Int>>()
    var values = validValues.indices.map { listOf(it) }
    while (values.isNotEmpty()) {
      result.addAll(values
          .map { list -> list.map { validValues[it] } }
          .filter { it.sum() == target }
          .map { it.sorted() })

      values = values.flatMap { list ->
        validValues.indices.mapNotNull { index ->
          if (index !in list && list.fold(validValues[index]) { acc, i -> acc + validValues[i] } <= target) {
            (list + index).sorted()
          } else null
        }
      }.distinct()
    }
    return result.toList()
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(10, 1, 2, 7, 6, 1, 5), 8, setOf(listOf(1, 7), listOf(1, 2, 5), listOf(2, 6), listOf(1, 1, 6))),
      Triple(intArrayOf(14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33, 20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12), 27,
          setOf(listOf(6, 6, 7, 8), listOf(6, 7, 14), listOf(6, 8, 13), listOf(6, 9, 12), listOf(6, 10, 11), listOf(6, 21), listOf(7, 8, 12), listOf(7, 9, 11), listOf(7, 20), listOf(8, 8, 11), listOf(8, 9, 10), listOf(9, 9, 9), listOf(9, 18), listOf(10, 17), listOf(11, 16), listOf(13, 14), listOf(27)))
  )) { (input, target, value) -> value to CombinationSumII().execute(input, target).toSet() }
}