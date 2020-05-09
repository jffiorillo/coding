package io.permutation

// https://leetcode.com/problems/next-permutation/
class NextPermutations {

  fun execute(input: IntArray) {
    var index = input.lastIndex - 1
    while (index in input.indices && input[index + 1] <= input[index]) index--
    if (index in input.indices) {
      var j = input.lastIndex
      while (j >= 0 && input[j] <= input[index]) j--
      input.swap(index, j)
    }
    input.reverse(index + 1)
  }

  private fun IntArray.reverse(begin: Int) {
    var start = begin
    var end = lastIndex
    while (start < end) {
      this.swap(start, end)
      start++
      end--
    }
  }

  private fun IntArray.swap(i: Int, j: Int) {
    this[i].let {
      this[i] = this[j]
      this[j] = it
    }
  }

}