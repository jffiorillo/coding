package io.queue

import java.util.*

// https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1394/
class ReversePolishNotation {

  private val operators = arrayOf("+", "-", "*", "/")

  fun execute(tokens: Array<String>): Int {
    val stack = Stack<Int>()
    tokens.map { token ->
      if (operators.contains(token)) {
        val second = stack.pop()
        val first = stack.pop()
        when (token) {
          "+" -> stack.push(first + second)
          "-" -> stack.push(first - second)
          "*" -> stack.push(first * second)
          "/" -> stack.push(first / second)
          else -> throw Exception("The following character $token is not recognized ")
        }
      } else {
        stack.push(token.toInt())
      }
    }
    return stack.pop()
  }
}

fun main() {
  val input = arrayOf("4", "13", "5", "/", "+")

  val reversePolishNotation = ReversePolishNotation()
  println("${reversePolishNotation.execute(input)}")
}