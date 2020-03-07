package io.queue

import java.util.*

class ValidateParentheses {
  private val openCharMap = mapOf(Pair('(', 0), Pair('{', 1), Pair('[', 2))
  private val closeCharMap = mapOf(Pair(')', 0), Pair('}', 1), Pair(']', 2))

  fun execute(input: String): Boolean = when {
    input.isEmpty() -> true
    else -> {
      val stack = Stack<Char>()
      val openChar = openCharMap.keys
      input.fold(true) { acc, char ->
        acc &&
            if (openChar.contains(char)) {
              stack.push(char)
              true
            } else if (stack.isEmpty() || openCharMap[stack.pop()] != closeCharMap[char]) {
              false
            } else {
              acc
            }
      } && stack.isEmpty()
    }
  }
}