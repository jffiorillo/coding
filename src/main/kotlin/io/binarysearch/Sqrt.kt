package io.binarysearch

import kotlin.math.sqrt

// https://leetcode.com/explore/learn/card/binary-search/125/template-i/950/
class Sqrt {

  fun executeRecursive(target: Int, begin: Int = 0, end: Int = sqrt(Int.MAX_VALUE.toDouble()).toInt()): Int = when {
    target < 2 -> target
    target < 4 -> 1
    begin == end -> begin
    else -> {
      val pivot = (begin + end) / 2
      val value = pivot * pivot
      val nexValue = (pivot + 1) * (pivot + 1)
      when {
        value == target || (target in (value + 1) until nexValue) -> pivot
        value < target -> executeRecursive(target, pivot + 1, end)
        else -> executeRecursive(target, begin, pivot - 1)
      }
    }
  }

  fun executeIterative(target: Int): Int {
    var begin = minOf(2, target)
    var end = sqrt(Int.MAX_VALUE.toDouble()).toInt()
    while (begin < end) {
      val pivot = (begin + end) / 2
      val value = pivot * pivot
      val nexValue = (pivot + 1) * (pivot + 1)
      when {
        value == target || (target in (value + 1) until nexValue) -> return pivot
        value < target -> begin = pivot + 1
        else -> end = pivot - 1
      }
    }
    return -1
  }
}


fun main() {
  val sqrt = Sqrt()
  val target = 2147395599
  println("${sqrt.executeIterative(target)} ${sqrt(target.toDouble()).toInt()}")
}