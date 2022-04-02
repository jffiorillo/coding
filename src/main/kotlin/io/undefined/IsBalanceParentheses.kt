package io.undefined

import io.undefined.IsBalanceParentheses.Parameter

class IsBalanceParentheses {

  data class Parameter(
    val input: String,
    val bruteForceSolution: String,
    val stackSolution: String,
    val backAndForthSolution: String,
    val debug: Boolean = false
  )

  fun balanceBruceForce(input: String, debug: Boolean = false): String {
    val shouldKeep: MutableList<Boolean> = input.map { false }.toMutableList()
    input.mapIndexed { index, value ->
      if (value == '(') {
        var j = index
        while (j < input.length) {
          if (input[j] == ')' && !shouldKeep[j]) {
            shouldKeep[index] = true
            shouldKeep[j] = true
            break
          }
          j++
        }
      }
      if (debug) {
        println("index $index value $value $shouldKeep")
      }
    }
    val result = StringBuilder()
    if (debug) {
      println(shouldKeep)
    }
    input.mapIndexed { index, value ->
      if (shouldKeep[index] || value.isLetter()) {
        result.append(value)
      }
    }

    return result.toString()
  }

  fun balanceStack(input: String, debug: Boolean = false): String {
    val stack = mutableListOf<Int>()
    val temp: MutableList<Char> = input.mapIndexed { index, value ->
      when (value) {
        '(' -> value.also { stack.add(index) }
        ')' -> if (stack.isNotEmpty()) value.also { stack.removeLast() } else '*'
        else -> value
      }.also { result ->
        if (debug)
          println("index $index value $value stack $stack result $result")
      }
    }.toMutableList()
    if (debug)
      println("stack $stack temp $temp")
    stack.forEach { temp[it] = '*' }
    return temp.fold("") { acc, value -> if (value == '*') acc else acc + value }
  }

  fun balanceBackAndForth(input: String, debug: Boolean = false): String {
    var countExtraParentheses = 0
    val temp: MutableList<Char> = input.map { value ->
      when (value) {
        '(' -> value.also { countExtraParentheses++ }
        ')' -> if (countExtraParentheses == 0) '*' else value.also { countExtraParentheses-- }
        else -> value
      }.also { result ->
        if (debug)
          println("value $value countExtraParentheses $countExtraParentheses result $result")
      }
    }.toMutableList()

    if (debug)
      println("balanceBackAndForth: first round: temp $temp countExtraParentheses $countExtraParentheses")

    countExtraParentheses = 0
    return temp.asReversed().map { value ->
      if (debug) println("checking $value")
      when (value) {
        ')' -> value.also { countExtraParentheses++ }
        '(' -> if (countExtraParentheses == 0) '*'.also { if (debug) println("balanceBackAndForth: second round: removing $value in $temp") } else value.also { countExtraParentheses-- }
        else -> value
      }
    }.asReversed().fold("") { acc, value -> if (value == '*') acc else acc + value }
  }

}

fun main() {
  listOf(
    Parameter("()", "()", "()", "()"),
    Parameter("a(b)c)", "a(b)c", "a(b)c", "a(b)c"),
    Parameter("(((((", "", "", ""),
    Parameter("(()()(", "(())", "()()", "()()"),
    Parameter(")(())(", "(())", "(())", "(())"),
    Parameter(")())(()()(", "()(())", "()()()", "()()()"),
  ).map { (input, bruteForceSolution, stackSolution, backAndForthSolution, debug) ->
    IsBalanceParentheses().apply {
      if (balanceBruceForce(input) == bruteForceSolution) {
        println("balanceBruceForce: '$input' is valid")
      } else {
        println(
          "balanceBruceForce: '$input' expected '$bruteForceSolution' but got '${
            balanceBruceForce(
              input,
              debug
            )
          }' instead."
        )
      }
      if (balanceStack(input) == stackSolution) {
        println("balanceStack: '$input' is valid")
      } else {
        println(
          "balanceStack: '$input' expected '$stackSolution' but got '${
            balanceStack(
              input,
              debug
            )
          }' instead."
        )
      }
      if (balanceBackAndForth(input) == backAndForthSolution) {
        println("balanceBackAndForth: '$input' is valid")
      } else {
        println(
          "balanceBackAndForth: '$input' expected '$backAndForthSolution' but got '${
            balanceBackAndForth(
              input,
              debug
            )
          }' instead."
        )
      }
      println("----------")
    }
  }
}