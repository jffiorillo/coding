package io.matrix

import io.utils.runTests

// https://leetcode.com/problems/spiral-matrix-ii/
class SpiralMatrixII {

  fun execute(size: Int): Array<IntArray> = Array(size){IntArray(size)}.apply {
    var counter = 1
    var r0 = 0
    var r1 = size-1
    var c0 = 0
    var c1 = size-1
    while(r0 <=r1 && c0 <= c1){
      (c0..c1).forEach { this[r0][it] = counter++ }
      (r0+1..r1).forEach { this[it][c1] = counter++ }
      if (r0 < r1 && c0 < c1){
        (c1-1 downTo c0).forEach { this[r1][it] = counter++ }
        (r1-1 downTo r0+1).forEach { this[it][c0] = counter++ }
      }
      r0++
      r1--
      c0++
      c1--
    }
  }
}

fun main(){
  runTests(
      listOf(
          3 to listOf(listOf(1,2,3),listOf(8,9,4), listOf(7,6,5))
      )
  ){ (input,value) -> value to SpiralMatrixII().execute(input).map { it.toList() }.toList() }
}