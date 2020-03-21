package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/144/more-practices/1034/
class TwoArrayIntersection {

  fun execute(input0: IntArray, input1: IntArray): IntArray =
      (input0.toHashSet() to input1.toHashSet()).let { (first, second) ->
        first.retainAll(second)
        first.toIntArray()
      }
}