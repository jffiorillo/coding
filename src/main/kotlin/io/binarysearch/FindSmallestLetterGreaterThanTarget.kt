package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/137/conclusion/977/
class FindSmallestLetterGreaterThanTarget {

  fun execute(letters: CharArray, target: Char): Char {
    var start = 0
    var end = letters.size
    val targetInt = target.toInt()
    while (start < end) {
      val pivot = start + (end - start) / 2
      if (letters[pivot].toInt() <= targetInt)
        start = pivot + 1
      else
        end = pivot
    }
    return when {
      start < letters.size && letters[start] > target -> letters[start]
      start+1 < letters.size && letters[start + 1] > target -> letters[start + 1]
      else -> letters.firstOrNull() ?: target
    }
  }
}