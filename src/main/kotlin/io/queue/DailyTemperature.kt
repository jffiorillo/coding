package io.queue

import java.util.*


class DailyTemperature {

  fun execute(input: IntArray): IntArray {
    val result = IntArray(input.size)
    val stack = Stack<Int>()
    for (i in input.indices.reversed()) {
      while (stack.isNotEmpty() && input[i] >= input[stack.peek()]) stack.pop()
      result[i] = if (stack.isEmpty()) 0 else stack.peek() - i
      stack.push(i)
    }
    return result
  }
}

fun main() {
  val input = intArrayOf(73, 74, 75, 71, 100, 69, 72, 76, 73)
  DailyTemperature().execute(input)
}