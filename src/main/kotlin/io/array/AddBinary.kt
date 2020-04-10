package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1160/
class AddBinary {

  fun execute(input0: String, input1: String): String {

    var extra = 0
    val builder = StringBuilder()
    val minLength = minOf(input0.length, input1.length)
    (0 until minLength).forEach { index ->
      val i0 = input0[input0.length - 1 - index].toString().toInt()
      val i1 = input1[input1.length - 1 - index].toString().toInt()
      when (i0 + i1 + extra) {
        0 -> builder.insert(0, '0').also { extra = 0 }
        1 -> builder.insert(0, '1').also { extra = 0 }
        2 -> builder.insert(0, '0').also { extra = 1 }
        3 -> builder.insert(0, '1').also { extra = 1 }
      }
    }
    when {
      input0.length != input1.length -> {
        val prefix = (if (input0.length > input1.length) input0 else input1).let { it.substring(0, it.length - minLength) }.let {
          if (extra == 1)
            execute("1", it)
          else it
        }
        builder.insert(0, prefix)
      }
      extra == 1 -> builder.insert(0, '1')
    }
    return builder.toString()
  }
}


fun main() {
  runTests(listOf(
      Triple("1", "11", "100"),
      Triple("01", "10", "11"),
      Triple("1010", "1011", "10101"),
      Triple("1", "111", "1000"),
      Triple("111", "1010", "10001")
  )) { (input0, input1, value) -> value to AddBinary().execute(input0, input1) }
}