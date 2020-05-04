package io.math

import io.utils.runTests
import java.util.*
import kotlin.math.absoluteValue


// https://leetcode.com/problems/fraction-to-recurring-decimal/
class FractionToDecimal {

  fun execute(numerator: Int, denominator: Int): String? {
    if (numerator == 0) {
      return "0"
    }
    val res = StringBuilder()
    // "+" or "-"
    res.append(if ((numerator > 0) xor (denominator > 0)) "-" else "")
    var currentNumerator = numerator.toLong().absoluteValue
    val currentDenominator = denominator.toLong().absoluteValue

    // integral part
    res.append(currentNumerator / currentDenominator)
    currentNumerator %= currentDenominator
    if (currentNumerator == 0L) return res.toString()

    // fractional part
    res.append(".")
    val map = HashMap<Long, Int>()
    map[currentNumerator] = res.length
    while (currentNumerator != 0L) {
      currentNumerator *= 10
      res.append(currentNumerator / currentDenominator)
      currentNumerator %= currentDenominator
      if (map.containsKey(currentNumerator)) {
        val index = map.getValue(currentNumerator)
        res.insert(index, "(")
        res.append(")")
        break
      } else map[currentNumerator] = res.length
    }
    return res.toString()
  }
}

fun main() {
  runTests(listOf(
      Triple(1, 2, "0.5"),
      Triple(2, 1, "2"),
      Triple(1, 3, "0.(3)")
  )) { (numerator, denominator, value) -> value to FractionToDecimal().execute(numerator, denominator) }
}