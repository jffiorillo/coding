package io.binarysearch

import io.utils.runTests

class BadVersionK(private val firstBadVersion: Int) {

  fun execute(numberOfCommits: Int): Int {
    var start = 0
    var end = numberOfCommits
    while (start <= end) {
      val pivot = start + (end - start) / 2
      if (isBadVersion(pivot))
        end = pivot - 1
      else start = pivot + 1
    }
    return start
  }

  fun isBadVersion(version: Int): Boolean = version >= firstBadVersion
}

fun main() {
  runTests(listOf(
      10 to 3,
      15 to 8,
      3 to 2,
      30 to 1,
      30 to 28
  )) { (input, value) -> value to BadVersionK(value).execute(input) }
}