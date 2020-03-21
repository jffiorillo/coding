package io.recursion

import kotlin.math.pow

//https://leetcode.com/explore/featured/card/recursion-i/256/complexity-analysis/2380/
class Pow {

  fun executeRecursive(x: Double, n: Int): Double = when {
    n == 0 -> 1.0
    n < 0 -> executeRecursive(x, n + 1) / x
    else -> executeRecursive(x, n - 1) * x
  }

  fun executeIterative(x: Double, n: Int): Double = when {
    n == 0 -> 1.0
    x == 1.0 -> x
    x == -1.0 -> if (n > 0) x else -x
    n > Int.MAX_VALUE / 2 || n < Int.MIN_VALUE / 2 -> 0.0
    n < 0 ->
      (n until 1).fold(x) { acc, _ -> acc / x }

    else -> (1 until n).fold(x) { acc, _ -> acc * x }
  }
}


fun main() {
  println("${2.0.pow(Int.MIN_VALUE.toDouble())}")
}