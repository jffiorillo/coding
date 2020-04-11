package io.array

import io.sort.quickSort
import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1154/
class ArrayPairMin {

  fun execute(nums: IntArray): Int {
    val sortedList = nums.toList().quickSort()
    var accum = 0
    for (index in sortedList.indices step 2) {
      accum += sortedList[index]
    }
    return accum
  }
}

fun main(){
  runTests(listOf(
   intArrayOf(1,4,3,2) to 4
  )){(input,value) -> value to ArrayPairMin().execute(input)}
}