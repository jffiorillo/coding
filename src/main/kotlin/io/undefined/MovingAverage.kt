@file:Suppress("unused")

package io.undefined

// https://leetcode.com/problems/moving-average-from-data-stream/
class MovingAverage(private val size: Int) {
  private val numbers = IntArray(size)
  private var count = 0


  fun next(`val`: Int): Double {
    numbers[count.rem(size)] = `val`
    count++
    return numbers.sum().toDouble() / minOf(count, size)
  }
}