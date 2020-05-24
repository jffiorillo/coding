package io.string

import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/one-edit-distance/
class OneEditDistance {

  fun execute(input: String, value: String, index: Int = 0, indexValue: Int = 0, changed: Boolean = false): Boolean {
    if ((input.length - value.length).absoluteValue > 1) return false
    var j = indexValue
    var i = index
    loop@ while (i < input.length && j < value.length) {
      when {
        input[i] != value[j] && changed -> return false
        input[i] != value[j] -> return execute(input, value, i, j + 1, true) || execute(input, value, i + 1, j + 1, true) || execute(input, value, i + 1, j, true)
      }
      i++
      j++
    }
    return if (i == input.length && j == value.length) changed else !changed
  }
}

fun main() {
  runTests(listOf(
      Triple("ab", "acb", true),
      Triple("", "", false),
      Triple("a", "", true)
  )) { (input, v, value) -> value to OneEditDistance().execute(input, v) }
}