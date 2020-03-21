package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1035/
class TwoNumberSum {

  fun executeSimpler(numbers: IntArray, target: Int): IntArray {
    var index1 = 0
    var index2 = numbers.size - 1
    while (numbers[index1] + numbers[index2] != target) {
      val sum = numbers[index1] + numbers[index2]
      if (sum > target) {
        index2--
      } else if (sum < target) {
        index1++
      }
    }
    return intArrayOf(index1 + 1, index2 + 1)
  }

  fun execute(numbers: IntArray, target: Int): IntArray {
    numbers.mapIndexed { index, value ->
      binarySearch(numbers, target - value, index)?.let { otherIndex ->
        return intArrayOf(index + 1, otherIndex + 1)
      }
    }
    return intArrayOf()
  }

  fun binarySearch(input: IntArray, target: Int, excludeIndex: Int): Int? {
    var start = 0
    var end = input.size - 1
    while (start <= end) {
      val pivot = start + (end - start) / 2
      val value = input[pivot]
      when {
        value == target -> return when {
          pivot != excludeIndex -> pivot
          input.getOrNull(pivot - 1) == value -> pivot - 1
          input.getOrNull(pivot + 1) == value -> pivot + 1
          else -> null
        }
        value < target -> {
          start = pivot + 1
        }
        else -> end = pivot - 1
      }

    }
    return null
  }
}

fun main() {

}