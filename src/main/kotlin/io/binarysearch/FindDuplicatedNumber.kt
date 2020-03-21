package io.binarysearch

// https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1039/
class FindDuplicatedNumber {

  fun execute(nums: IntArray): Int {
    // Find the intersection point of the two runners.
    var tortoise = nums[nums.first()]
    var hare = nums[nums[nums.first()]]
    while (tortoise != hare) {
      tortoise = nums[tortoise]
      hare = nums[nums[hare]]
    }

    // Find the "entrance" to the cycle.
    var ptr1 = nums.first()
    var ptr2 = tortoise
    while (ptr1 != ptr2) {
      ptr1 = nums[ptr1]
      ptr2 = nums[ptr2]
    }
    return ptr1
  }
}