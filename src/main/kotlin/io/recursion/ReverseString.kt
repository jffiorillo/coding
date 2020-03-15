package io.recursion

//https://leetcode.com/explore/featured/card/recursion-i/250/principle-of-recursion/1440/
class ReverseString {

  fun execute(input: CharArray, left: Int = 0, right: Int = input.size): Unit = when {
    left < right -> input[left].let { temp ->
      input[left] = input[right]
      input[right] = temp
      execute(input,left+1,right -1)
    }
    else -> {}
  }

  fun helper(s: CharArray, l: Int, r: Int) {
    var left = l
    var right = r
    if (left >= right) return
    val tmp = s[left]
    s[left++] = s[right]
    s[right--] = tmp
    helper(s, left, right)
  }
}

fun main() {
  val reverseString = ReverseString()
  println(reverseString.execute(charArrayOf('h', 'e', 'l', 'l', 'o')))
}