package io.string

import io.utils.runTests

// https://leetcode.com/problems/multiply-strings/
class MultiplyStrings {
  fun execute(input0: String, input1: String): String = input0 * input1

  fun multiply(input0: String, input1: String): String? {
    val map = IntArray(input0.length + input1.length)
    for (i in input0.lastIndex downTo 0) {
      for (j in input1.lastIndex downTo 0) {
        val mul = input0[i].toInteger() * input1[j].toInteger()
        val p1 = i + j
        val p2 = i + j + 1
        val sum = mul + map[p2]
        map[p1] += sum / 10
        map[p2] = sum.rem(10)
      }
    }
    return map.fold(StringBuilder()) { acc, value ->
      acc.apply { if (isNotEmpty() || value != 0) append(value) }
    }.let { if (it.isEmpty()) "0" else it.toString() }
  }
}

private fun Char.toInteger() = this - '0'

operator fun String.times(another: String): String {

  fun multiply(anotherIndex: Int): String {

    var accumulator = 0
    val result = StringBuilder()
    repeat(another.lastIndex - anotherIndex) { result.append('0') }
    val multiply = another[anotherIndex].toInteger()
    (this.lastIndex downTo 0).forEach { index ->
      val sum = this[index].toInteger() * multiply + accumulator
      result.append(sum.rem(10))
      accumulator = sum / 10
    }
    if (accumulator > 0)
      result.append(accumulator)
    return result.reverse().toString()
  }

  if (this.all { it == '0' } || another.all { it == '0' }) return "0"
  var result: String? = null
  (another.lastIndex downTo 0).forEach { index ->
    if (another[index].toInteger() != 0) {
      val multiply = multiply(index)
      result = result?.let { it `+` multiply } ?: multiply
    }
  }

  return result ?: "0"
}

@Suppress("FunctionName")
infix fun String.`+`(another: String): String {
  if (another.isEmpty()) return this
  if (this.isEmpty()) return another
  var index = this.lastIndex
  var anotherIndex = another.lastIndex
  val result = StringBuilder()
  var accumulator = 0
  while (index >= 0 && anotherIndex >= 0) {
    val first = this[index].toInteger()
    val second = another[anotherIndex].toInteger()
    val sum = first + second + accumulator
    result.append(sum.rem(10))
    accumulator = sum / 10
    index--
    anotherIndex--
  }
  if (index >= 0 || anotherIndex >= 0) {
    while (index >= 0) {
      val first = this[index].toInteger()
      val sum = first + accumulator
      result.append(sum.rem(10))
      accumulator = sum / 10
      index--
    }

    while (anotherIndex >= 0) {
      val first = another[anotherIndex].toInteger()
      val sum = first + accumulator
      result.append(sum.rem(10))
      accumulator = sum / 10
      anotherIndex--
    }
  }
  if (accumulator != 0)
    result.append(accumulator)
  return result.reverse().toString()
}

fun main() {
  runTests(listOf(
      Triple("123", "2", "246"),
      Triple("9", "9", "81"),
      Triple("10", "10", "100"),
      Triple("123", "456", "56088"),
      Triple("52", "0", "0"),
      Triple("0", "52", "0")

  )) { (input0, input1, value) -> value to MultiplyStrings().multiply(input0, input1) }
}