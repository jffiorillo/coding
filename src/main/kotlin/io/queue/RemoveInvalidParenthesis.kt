@file:Suppress("unused")

package io.queue

import java.util.*

// https://leetcode.com/problems/remove-invalid-parentheses/
class RemoveInvalidParenthesis {
  fun execute(input: String): List<String> {
    if (input.isValid()) return listOf(input)
    val result = mutableListOf<String>()
    val stack = LinkedList<Pair<String, Int>>()
    val visited = mutableSetOf<String>()
    stack.add(input to 0)
    while (stack.isNotEmpty()) {
      val (value, start) = stack.pop()
      if (visited.contains(value)) continue
      visited.add(value)
      when {
        value.isValid() -> result.add(value)
        result.isEmpty() -> {
          for (index in start until value.length) {
            val char = value[index]
            when {
              (char == '(' || char == ')') && (index == start || value[index] != value[index - 1]) ->
                stack.add(value.substring(0, index) + value.substring(index + 1) to index)
            }
          }
        }
      }
    }
    return result
  }
}

private fun String.isValid(): Boolean {
  if (isEmpty()) return true
  var count = 0

  forEach { char ->
    when {
      char == '(' -> count++
      char == ')' && count == 0 -> return false
      char == ')' -> count--
    }
  }
  return count == 0
}