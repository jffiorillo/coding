package io.dp

import io.utils.runTests

// https://ianna1009.gitbooks.io/leectcode/Cracking%20Coding%20Interview/Chapter8.%20Recursion%20and%20Dynamic%20Programming/83_magic_index.html
class MagicIndex {

  fun execute(input: IntArray): Int? {
    var start = 0
    var end = input.size
    while (start <= end) {
      val pivot = start + (end - start) / 2
      when {
        pivot == input[pivot] -> return pivot
        input[pivot] < pivot -> start = pivot + 1
        else -> end = pivot - 1
      }
    }
    return null
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(-1, 0, 1, 2, 4, 6, 8, 10, 13, 16) to 4,
      intArrayOf(-1, 0, 1, 2, 3, 6, 8, 10, 13, 16) to null
  )) { (input, value) -> value to MagicIndex().execute(input) }
}