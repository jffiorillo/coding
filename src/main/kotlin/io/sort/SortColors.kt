package io.sort

// https://leetcode.com/problems/sort-colors/
class SortColors {

  fun execute(input: IntArray) {
    var lastIndex = 0
    var color = 0
    repeat(2) {
      input.forEachIndexed { index, value ->
        when (value) {
          color -> {
            input[index] = input[lastIndex]
            input[lastIndex++] = color
          }
        }
      }
      color++
    }
  }
}