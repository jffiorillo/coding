@file:Suppress("MemberVisibilityCanBePrivate")

package io.binarysearch


// https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1029/
class TwoArrayIntersectionWithRepeatNumbers {

  fun execute(input0: IntArray, input1: IntArray): IntArray {
    quicksort(input0)
    quicksort(input1)
    var i = 0
    var j = 0
    val result = mutableListOf<Int>()
    while (i < input0.size && j < input1.size) {
      when {
        input0[i] == input1[j] -> {
          result.add(input0[i])
          i++
          j++
        }
        input0[i] < input1[j] -> i++
        else -> j++
      }
    }
    return result.toIntArray()
  }

  fun quicksort(input: IntArray, start: Int = 0, end: Int = input.size - 1) {
    if (start < end) {
      val pivot = partition(input, start, end)
      quicksort(input, start, pivot - 1)
      quicksort(input, pivot + 1, end)
    }
  }

  fun partition(input: IntArray, start: Int, end: Int): Int {
    val pivot = input[end]
    var i = start - 1
    for (j in start until end) {
      if (input[j] < pivot) {
        i++
        input.swap(i, j)
      }
    }
    input.swap(i + 1, end)
    return i + 1
  }

  private fun IntArray.swap(index0: Int, index1: Int) = this.also {
    this[index0].let { temp ->
      this[index0] = this[index1]
      this[index1] = temp
    }
  }

  @Suppress("ArrayInDataClass")
  data class Wrapper(val input0: IntArray, val input1: IntArray, val output: List<Int>)
}

fun main() {
  val twoArrayIntersectionWithRepeatNumbers = TwoArrayIntersectionWithRepeatNumbers()
  listOf(
      TwoArrayIntersectionWithRepeatNumbers.Wrapper(intArrayOf(9, 4, 9, 8, 4), intArrayOf(4, 9, 5), listOf(4, 9))
  ).map { (input0, input1, result) ->
    val output = twoArrayIntersectionWithRepeatNumbers.execute(input0, input1)
    println("${output.toList() == result}")
  }
}