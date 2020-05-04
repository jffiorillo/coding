@file:Suppress("unused")

package io.array


// https://leetcode.com/problems/wiggle-sort-ii/
class WiggleSort {

  fun execute(input: IntArray) {

    //sort the array
    input.sort()

    // find mid point of array
    val middleOrArray = (input.size - 1) / 2
    var mid = middleOrArray
    var right = input.lastIndex

    //aux array to temp store the values
    val result = IntArray(input.size)
    var counter = 0

    // select values from two parts of the array and arrange them in aux array
    while (mid >= 0 || right > middleOrArray) {
      if (counter % 2 == 0) {
        result[counter] = input[mid]
        mid--
      } else {
        result[counter] = input[right]
        right--
      }
      counter++
    }

    //now store back these values in input/original array
    result.forEachIndexed { index, value -> input[index] = value }
  }

  // this one works with unique values
  fun executeUnique(input: IntArray) {
    if (input.size <= 1) return

    var index = 0
    while (index < input.size) {
      if (index - 1 in input.indices && input[index - 1] > input[index]) {
        input.swap(index, index - 1)
      }
      if (index + 1 in input.indices && input[index + 1] > input[index]) {
        input.swap(index, index + 1)
      }
      index += 2
    }
  }

  private fun IntArray.swap(index: Int, index1: Int) {
    val temp = this[index]
    this[index] = this[index1]
    this[index1] = temp
  }
}