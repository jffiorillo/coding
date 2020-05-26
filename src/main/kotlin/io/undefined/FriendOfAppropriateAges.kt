package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/friends-of-appropriate-ages/
class FriendOfAppropriateAges {

  fun execute(input: IntArray): Int {
    val count = input.fold(IntArray(121)) { acc, value -> acc.apply { this[value]++ } }
    var result = 0
    count.forEachIndexed { ageA, numberOfPeople ->
      for (ageB in count.indices) {
        if (ageA * 0.5 + 7 >= ageB) continue
        if (ageA < ageB) continue
        result += numberOfPeople * count[ageB]
        if (ageA == ageB) result -= numberOfPeople
      }
    }
    return result
  }

  fun numFriendRequests(input: IntArray): Int {
    val numInAge = input.fold(IntArray(121)) { acc, value -> acc.apply { this[value]++ } }
    val sumInAge = IntArray(121)
    for (i in 1..120) sumInAge[i] = numInAge[i] + sumInAge[i - 1]
    var result = 0
    for (i in 15..120) {
      if (numInAge[i] == 0) continue
      val accum = sumInAge[i] - sumInAge[i / 2 + 7]
      result += accum * numInAge[i] - numInAge[i] //people will not friend request themselves, so  - numInAge[i]
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(16, 16) to 2
  )) { (input, value) -> value to FriendOfAppropriateAges().execute(input) }
}