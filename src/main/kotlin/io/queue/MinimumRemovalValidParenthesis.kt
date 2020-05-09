package io.queue

import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
class MinimumRemovalValidParenthesis {

  fun execute(input: String): String {
    val stack = LinkedList<Int>()
    val extraCloseParenthesis = mutableSetOf<Int>()
    input.forEachIndexed { index, char ->
      when {
        char == '(' -> stack.push(index)
        char == ')' && stack.isNotEmpty() -> stack.pop()
        char == ')' -> extraCloseParenthesis.add(index)
      }
    }
    extraCloseParenthesis.addAll(stack)
    return input.foldIndexed(StringBuilder()) { index, acc, char ->
      acc.apply { if (!extraCloseParenthesis.contains(index)) append(char) }
    }.toString()
  }
}

fun main() {
  runTests(listOf(
      "lee(t(c)o)de)" to "lee(t(c)o)de",
      "a)b(c)d" to "ab(c)d",
      "))((" to "",
      "(a(b(c)d)" to "a(b(c)d)"
  )) { (input, value) -> value to MinimumRemovalValidParenthesis().execute(input) }
}