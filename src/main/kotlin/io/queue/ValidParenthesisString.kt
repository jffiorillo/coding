package io.queue

import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/valid-parenthesis-string/
class ValidParenthesisString {

  fun execute(input: String, q: Int = 0): Boolean = when {
    input.isEmpty() -> q == 0
    else -> {
      var queue = q
      input.forEachIndexed { index, char ->
        when {
          char == '*' && index + 1 == input.length -> {
            return queue == 0 || queue == 1
          }
          char == '*' -> return input.substring(index + 1).let { input1 -> execute(input1, queue) || execute("($input1", queue) || execute(")$input1", queue) }
          char == '(' -> queue++
          char == ')' && queue == 0 -> return false
          else -> queue--
        }
      }
      queue == 0
    }
  }
}

fun main() {
  runTests(listOf(
      "()" to true,
      "(*)" to true,
      "(*))" to true,
      "((*)*" to true,
      "*" to true,
      "(()" to false
  )) { (input, value) -> value to ValidParenthesisString().execute(input) }
}