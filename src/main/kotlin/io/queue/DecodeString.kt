package io.queue

import java.util.*
import kotlin.math.pow

class DecodeString {

  fun execute(input: String): String {
    val stack = Stack<String>()
    input.map { it.toString() }.map { char ->
      when {
        char == "[" -> stack.push(char)
        char == "]" -> pushOrAdd(stack, generateValue(stack))
        "\\d".toRegex().matches(char) -> stack.push(char)
        else -> pushOrAdd(stack, char)
      }
    }
    return generateSequence { if (stack.isNotEmpty()) stack.pop() else null }.toList().let {
      if (it.isEmpty()) "" else it.reduce { acc, s -> s + acc }
    }
  }

  private fun pushOrAdd(stack: Stack<String>, newValue: String) =
      if (stack.isNotEmpty() && "\\w+".toRegex().matches(stack.peek())) stack.push(stack.pop() + newValue)
      else stack.push(newValue)

  private fun generateValue(stack: Stack<String>) = extraString(stack).repeat(extraNumber(stack))

  private fun extraString(stack: Stack<String>) =
      generateSequence { if (stack.isNotEmpty() && stack.peek() != "[") stack.pop() else stack.pop().let { null } }.toList()
          .let { strings -> if (strings.isEmpty()) "" else strings.reduce { acc, char -> acc + char } }

  private fun extraNumber(stack: Stack<String>): Int =
      generateSequence { if (stack.isNotEmpty() && "\\d".toRegex().matches(stack.peek())) stack.pop().toString().toInt() else null }
          .mapIndexed { index, number -> 10.toDouble().pow(index.toDouble()).toInt() * number }.toList().let { numbersSequence ->
            if (numbersSequence.isEmpty()) 1 else numbersSequence.reduce { acc, new -> acc + new }
          }
}


fun main() {

  val decodeString = DecodeString()

  listOf(
      Pair("3[a]2[bc]", "aaabcbc"),
      Pair("3[a2[c]]", "accaccacc"),
      Pair("2[abc]3[cd]ef", "abcabccdcdcdef")
  ).map { (input, answer) ->
    val output = decodeString.execute(input)
    println("$input $output answer $answer is valid ${output == answer}")
  }
}