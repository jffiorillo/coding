package io.math

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/divide-two-integers/
class DivideTwoIntegers {


  // Time O(logN)
  // Space O(logN)
  fun executeWithExtraSpace(dividendInput: Int, divisor: Int): Int {
    // Special case: overflow.
    if (dividendInput == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
    if (divisor == 1) return dividendInput
    val isNegative = (dividendInput > 0) xor (divisor > 0)
    var dividend = dividendInput.absoluteValue
    val dp = generateDp(dividendInput.absoluteValue, divisor.absoluteValue)
    var result = 0
    if (dp.size == 1)
      result++
    else (dp.lastIndex downTo 0).forEach { index ->
      val (power, value) = dp[index]
      if (value < dividend) {
        result += power
        dividend -= value
      }
    }
    return if (isNegative) -result else result
  }

  private fun generateDp(dividend: Int, divisor: Int) = mutableListOf<Pair<Int, Int>>().apply {
    var powerOfTwo = 1
    var value = divisor
    while (value <= dividend) {
      add(powerOfTwo to value)
      powerOfTwo += powerOfTwo
      value += value
    }
  }

  // Time O(log2N)
  // Space O(1)
  fun execute0(dividendInput: Int, divisorInput: Int): Int {
    // Special case: overflow.
    if (dividendInput == Int.MIN_VALUE && divisorInput == -1) return Int.MAX_VALUE
    var dividend = dividendInput.absoluteValue
    val divisor = divisorInput.absoluteValue
    val isNegative = (dividendInput > 0) xor (divisorInput > 0)
    var result = 0
    while (divisor <= dividend) {
      var powerOfTwo = 1
      var value = divisor
      while (value + value <= dividend) {
//      while (value >= HALF_INT_MIN && value + value >= dividend) {
        value += value
        powerOfTwo += powerOfTwo
      }
      result += powerOfTwo
      dividend -= value
    }
    return if (isNegative) -result else result
  }

  // Time O(logN)
  //Space O(1)
  fun execute(dividendInput: Int, divisorInput: Int): Int {
    // Special case: overflow.
    if (dividendInput == Int.MIN_VALUE && divisorInput == -1) return Int.MAX_VALUE
    val isNegative = (dividendInput > 0) xor (divisorInput > 0)
    if (divisorInput.absoluteValue == 1) return if (isNegative) -(dividendInput.absoluteValue) else dividendInput.absoluteValue

    var dividend = dividendInput.absoluteValue
    val divisor = divisorInput.absoluteValue

    var highestDouble = divisor
    var highestPowerOfTwo = 1
    while (dividend >= highestDouble + highestDouble) {
      highestPowerOfTwo += highestPowerOfTwo
      highestDouble += highestDouble
    }

    var result = 0
    while (dividend >= divisor) {
      if (dividend >= highestDouble) {
        result += highestPowerOfTwo
        dividend -= highestDouble
      }
      highestPowerOfTwo = highestPowerOfTwo shr 1
      highestDouble = highestDouble shr 1
    }

    return if (isNegative) -result else result
  }
}

fun main() {
  runTests(listOf(
      Triple(10, 3, 3),
      Triple(10, -3, -3),
      Triple(Int.MAX_VALUE, 1, Int.MAX_VALUE),
      Triple(1, -1, -1),
      Triple(Int.MAX_VALUE, 2, Int.MAX_VALUE / 2)
  )) { (dividend, divisor, result) -> result to DivideTwoIntegers().execute(dividend, divisor) }
}