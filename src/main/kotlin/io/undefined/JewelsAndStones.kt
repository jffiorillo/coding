@file:Suppress("unused")

package io.undefined

// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3317/
class JewelsAndStones {

  fun execute(jewels: String, stones: String): Int {
    var result = 0
    stones.forEach { char -> if (jewels.contains(char)) result++ }
    return result
  }
}