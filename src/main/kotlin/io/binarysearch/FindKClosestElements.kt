@file:Suppress("MemberVisibilityCanBePrivate")

package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/135/template-iii/945/
class FindKClosestElements {

  fun execute(input: IntArray, k: Int, target: Int): List<Int> = when {
    input.isEmpty() -> emptyList()
    k == input.size -> input.toList()
    else ->
      searchElement(input, target).let { targetIndex ->
        searchClosestElements(input, targetIndex, k, target).let { (start, end) ->
          input.toList().subList(start, minOf(end + 1, input.size))
        }
      }
  }


  fun searchClosestElements(input: IntArray, targetIndex: Int, k: Int, target: Int): Pair<Int, Int> {
    var left = targetIndex
    var right = targetIndex
    repeat(k - 1) {
      if (left < 1) {
        right++
      } else if (input.size - 1 <= right) {
        left--
      } else if (input[right + 1] - target < target - input[left - 1]) {
        right++
      } else {
        left--
      }
    }
    return left to right
  }

  fun searchElement(input: IntArray, target: Int): Int {
    var start = 0
    var end = input.size - 1
    while (start < end) {
      val pivot = start + (end - start) / 2
      val value = input[pivot]
      if (value == target) {
        return pivot
      } else if (value < target) {
        start = pivot + 1
      } else {
        end = pivot - 1
      }
    }
    return start
  }


  data class Wrapper(@Suppress("ArrayInDataClass") val input: IntArray, val k: Int, val target: Int, val solution: List<Int>)
}

fun main() {
  val findKClosestElements = FindKClosestElements()
  listOf(
      FindKClosestElements.Wrapper((1..9).toList().toIntArray(), 4, 3, (1..4).toList()),
      FindKClosestElements.Wrapper((1..9).toList().toIntArray(), 4, -1, (1..4).toList()),
      FindKClosestElements.Wrapper(intArrayOf(1, 1, 1, 10, 10, 10), 1, 9, listOf(10)),
      FindKClosestElements.Wrapper(intArrayOf(0, 1, 1, 1, 2, 3, 6, 7, 8, 9), 9, 4, listOf(0, 1, 1, 1, 2, 3, 6, 7, 8)),
      FindKClosestElements.Wrapper(intArrayOf(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5, listOf(3, 3, 4)),
      FindKClosestElements.Wrapper(intArrayOf(0, 0, 0, 1, 3, 5, 6, 7, 8, 8), 2, 2, listOf(1, 3)),
      FindKClosestElements.Wrapper(intArrayOf(0, 1, 2, 2, 2, 3, 6, 8, 8, 9), 5, 9, listOf(3, 6, 8, 8, 9)),
      FindKClosestElements.Wrapper(intArrayOf(1, 2, 3, 3, 6, 6, 7, 7, 9, 9), 8, 8, listOf(3, 3, 6, 6, 7, 7, 9, 9))
  ).map { (input, k, target, result) ->
    val output = findKClosestElements.execute(input, k, target)
    if (output == result) {
      println("Input ${input.toList()} k $k target $target , solution is valid")
    } else {
      println("INVALID Solution: $output, expected $result")

    }
  }
}