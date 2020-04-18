package io.math

import io.utils.runTests

// https://leetcode.com/problems/palindrome-number/solution/
class IntIsPalindrome {

  fun execute(input: Int): Boolean {
    if (input < 0 || input == Int.MAX_VALUE) return false
    var reverseInput = 0
    var current = input
    while (current > 0) {
      reverseInput = reverseInput * 10 + current.rem(10)
      current /= 10
    }
    return reverseInput == input
  }
}
fun main(){
  runTests(listOf(
      121 to true,
      10 to false,
      -121 to false,
      123321 to true,
      1234321 to true
  )){(input,value) -> value to IntIsPalindrome().execute(input)}
}