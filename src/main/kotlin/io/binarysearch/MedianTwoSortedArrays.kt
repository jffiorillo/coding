package io.binarysearch

import kotlin.math.max
import kotlin.math.min

//https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1040/
class MedianTwoSortedArrays {
  fun execute(input0: IntArray, input1: IntArray): Double {
    val smallInput = if (input0.size < input1.size) input0 else input1
    val largeInput = if (input0.size < input1.size) input1 else input0
    val smallSize = smallInput.size
    val largeSize = largeInput.size
    var iMin = 0
    var iMax = smallSize
    val halfLen = (smallSize + largeSize + 1) / 2
    while (iMin <= iMax) {
      val i = (iMin + iMax) / 2
      val j = halfLen - i
      if (i < iMax && largeInput[j - 1] > smallInput[i]) {
        iMin = i + 1 // i is too small
      } else if (i > iMin && smallInput[i - 1] > largeInput[j]) {
        iMax = i - 1 // i is too big
      } else { // i is perfect
        var maxLeft = 0
        maxLeft = when {
          i == 0 -> largeInput[j - 1]
          j == 0 -> smallInput[i - 1]
          else -> max(smallInput[i - 1], largeInput[j - 1])
        }
        if ((smallSize + largeSize) % 2 == 1) {
          return maxLeft.toDouble()
        }
        val minRight = when {
          i == smallSize -> {
            largeInput[j]
          }
          j == largeSize -> {
            smallInput[i]
          }
          else -> {
            min(largeInput[j], smallInput[i])
          }
        }
        return (maxLeft + minRight) / 2.0
      }
    }
    return 0.0
  }


}