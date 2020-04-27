package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/decode-ways/
class DecodeWays {

  fun execute(input: String): Int {
    if (input.isEmpty()) return 0
    val memoization = IntArray(input.length)
    memoization[input.lastIndex] = if (input.last() != '0') 1 else 0
    for (i in input.length - 2 downTo 0) {
      if (input[i] == '0') continue
      memoization[i] =
          when {
            input.substring(i, i + 2).toInt() <= 26 && i + 2 !in memoization.indices -> memoization[i + 1] + 1
            input.substring(i, i + 2).toInt() <= 26 -> memoization[i + 1] + memoization[i + 2]
            else -> memoization[i + 1]
          }
    }
    return memoization.first()
  }

}

fun main() {
  runTests(listOf(
      "12" to 2,
      "226" to 3,
      "206" to 1,
      "220" to 1,
      "27" to 1,
      "01" to 0
  )) { (input, value) -> value to DecodeWays().execute(input) }
}
