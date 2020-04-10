package io.array

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3289/
// https://medium.com/@navaneethrvce/counting-elements-leetcode-a60daed46d2d
class CountingElements {

  fun execute(input: IntArray) :Int=
    input.toSet().let { set ->
      input.fold(0) { acc, value -> if (set.contains(value+1)) acc+1 else acc }
    }
}