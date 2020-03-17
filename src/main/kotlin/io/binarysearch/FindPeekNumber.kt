package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/126/template-ii/948/
class FindPeekNumber {

  fun execute(nums: IntArray): Int{
    var start = 0
    var end = nums.size -1
    while (start < end) {
      val pivot = start + (end - start) / 2
      if(nums[pivot+1] < nums[pivot]) end = pivot
      else start = pivot+1
    }
    return start
  }
}


fun main(){
  val findPeekNumber = FindPeekNumber()
  println(findPeekNumber.execute(intArrayOf(1,2,1,3,5,6,4)))
}