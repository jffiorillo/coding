package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1148/
class PlusOne {

  fun execute(digits: IntArray) = when {
    digits.all { it == 9 } -> {
      IntArray(digits.size + 1) { if (it == 0) 1 else 0 }
    }
    digits.last() == 9 -> {
      digits.apply {
        for (i in size - 1 downTo 0) {
          if (this[i] != 9) {
            this[i]++
            break
          } else
            this[i] = 0
        }
      }
    }
    else -> digits.apply { this[digits.size - 1] = this.last() + 1 }
  }
}

fun main(){
  runTests(listOf(
          intArrayOf(0,0,0,1) to listOf(0,0,0,2),
          intArrayOf(0,3,9,9) to listOf(0,4,0,0),
          intArrayOf(9,9,9,9) to listOf(1,0,0,0,0)
      )
  ){(input,value) -> value to PlusOne().execute(input).toList() }
}