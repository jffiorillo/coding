package io.undefined


// https://leetcode.com/problems/largest-perimeter-triangle/
class LargestPerimeterTriangle {

  fun execute(input: IntArray): Int {
    input.sort()
    for (index in input.size - 3 downTo 0)
      if (input[index] + input[index + 1] > input[index + 2])
        return input[index] + input[index + 1] + input[index + 2]
    return 0
  }
}