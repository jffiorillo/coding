package io.dp

// https://leetcode.com/problems/count-numbers-with-unique-digits/
class CountNumbersWithUniqueDigits {

  // https://www.youtube.com/watch?v=OkJKxoDbQ_c
  fun execute1(digits: Int): Int = when (digits) {
    in Int.MIN_VALUE until 0 -> 0
    0 -> 1
    1 -> 10
    2 -> 91
    else -> {
      var product = 81
      var end = 8
      var current = 91
      var currentIndex = 3
      while (currentIndex <= digits) {
        product *= end
        end--
        current += product
        currentIndex++
      }
      current
    }
  }


  // https://leetcode.com/problems/count-numbers-with-unique-digits/discuss/83061/Java-O(1)-with-explanation
  fun execute(digits: Int): Int {
    if (digits == 0) return 1
    var result = 10
    var base = 9
    var index = 2
    while (index <= digits && index <= 10) {
      base *= (9 - index + 2)
      result += base
      index++
    }
    return result
  }
}