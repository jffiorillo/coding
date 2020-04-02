package io.math

import io.utils.runTests

// https://leetcode.com/problems/valid-boomerang/
class ValidBoomerang {

  fun execute(points: Array<IntArray>): Boolean {
    val dx1 = points.secondPoint().x() - points.firstPoint().x()
    val dx2 = points.thirdPoint().x() - points.secondPoint().x()

    val dy1 = points.secondPoint().y() - points.firstPoint().y()
    val dy2 = points.thirdPoint().y() - points.secondPoint().y()
    return dy1 * dx2 != dy2 * dx1
  }

  private fun <T> Array<T>.firstPoint() = this[0]
  private fun <T> Array<T>.secondPoint() = this[1]
  private fun <T> Array<T>.thirdPoint() = this[2]

  fun IntArray.x() = this[0]
  private fun IntArray.y() = this[1]
}

fun main() {

  runTests(
      listOf(
          arrayOf(intArrayOf(0, 0), intArrayOf(2, 1), intArrayOf(2, 1)) to false
      )
  ) { (input, value) -> Pair(value, ValidBoomerang().execute(input)) }
}