package io.queue

import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/basic-calculator-ii/
class BasicCalculatorII {
  // inspired by https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution
  fun execute(input: String): Int {
    var sign = '+'
    var num = 0
    val stack = LinkedList<Int>()
    input.forEachIndexed { index, char ->
      if (char.isDigit()) num = num * 10 + (char - '0')
      if (!char.isDigit() && char != ' ' || index == input.lastIndex) {
        when (sign) {
          '+' -> stack.push(num)
          '-' -> stack.push(-num)
          '*' -> stack.push(stack.pop() * num)
          '/' -> stack.push(stack.pop() / num)
        }
        sign = char
        num = 0
      }
    }
    return stack.fold(0) { acc, value -> acc + value }
  }

  fun execute1(i: String): Int {
    val input = i.replace(" ", "")
    val map = mutableMapOf<Int, Pair<Int, Int>>()
    var index = 0
    var lastValue = 0
    var lastIndexMap: Int? = null
    var lastValueIndex = 0
    while (index < input.length) {
      val char = input[index]
      when {
        char.isDigit() -> {
          lastValueIndex = index
          val (newLast, nextIndex) = readInteger(input, index)
          index = nextIndex
          lastValue = newLast
          lastIndexMap = null
        }
        char == '+' || char == '-' -> {
          index++
          lastIndexMap = null
        }
        char == '*' -> {
          val (value, nextIndex) = readInteger(input, index + 1)
          lastValue *= value
          map[lastIndexMap ?: lastValueIndex] = lastValue to nextIndex
          index = nextIndex
        }
        char == '/' -> {
          val (value, nextIndex) = readInteger(input, index + 1)
          lastValue /= value
          map[lastIndexMap ?: lastValueIndex] = lastValue to nextIndex
          index = nextIndex
        }
      }
    }
    index = 0
    var result = 0
    while (index < input.length) {
      val char = input[index]
      when {
        map.containsKey(index) -> {
          val (value, newIndex) = map.getValue(index)
          result += value
          index = newIndex
        }
        char.isDigit() -> {
          val (value, nextIndex) = readInteger(input, index)
          index = nextIndex
          result += value
        }
        char == '+' -> {
          val (value, newIndex) = if (map.containsKey(index + 1)) map.getValue(index + 1) else readInteger(input, index + 1)
          result += value
          index = newIndex
        }
        char == '-' -> {
          val (value, newIndex) = if (map.containsKey(index + 1)) map.getValue(index + 1) else readInteger(input, index + 1)
          result -= value
          index = newIndex
        }
      }
    }
    return result
  }

  private fun readInteger(input: String, index: Int): Pair<Int, Int> {
    var current = index
    var result = 0
    while (current < input.length && input[current].isDigit()) {
      result = 10 * result + (input[current] - '0')
      current++
    }
    return result to current
  }
}

fun main() {
  runTests(listOf(
      "3+2*2" to 7,
      "3/2 " to 1,
      " 3+5 / 2 " to 5
  )) { (input, value) -> value to BasicCalculatorII().execute(input) }
}