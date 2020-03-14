package io.recursion

import java.util.*

class GenerateParenthesis {

  fun executeIterative(amount: Int): List<String> = when (amount) {
    0 -> emptyList()
    else -> {
      val answers = mutableListOf<String>()
      val stack = LinkedList<Wrapper>()
      stack.push(Wrapper(amount, amount))
      while (stack.isNotEmpty()) {
        val current = stack.pop()
        if (current.canAddRightBracket())
          stack.push(current.addRightBracket())
        if (current.hasLeftBrackets())
          stack.push(current.addLeftBracket())
        if (current.isFinished())
          answers.add(current.value)
      }
      answers
    }
  }

  fun executeRecursive(current: Wrapper): List<String> = when {
    current.isFinished() -> listOf(current.value)
    else -> {
      (if (current.canAddRightBracket())
        executeRecursive(current.addRightBracket())
      else emptyList()) + if (current.hasLeftBrackets()) executeRecursive(current.addLeftBracket()) else emptyList()
    }
  }

  data class Wrapper(val leftBrackets: Int, val rightBrackets: Int, val value: String = "") {
    fun addRightBracket() = this.copy(rightBrackets = rightBrackets - 1, value = "$value)")
    fun addLeftBracket() = this.copy(leftBrackets = leftBrackets - 1, value = "$value(")
    fun isFinished() = this.leftBrackets == 0 && this.rightBrackets == 0

    fun hasLeftBrackets() = leftBrackets > 0

    fun canAddRightBracket() = leftBrackets < rightBrackets
  }
}

fun main() {
  val generateParenthesis = GenerateParenthesis()

  val numberOfBrackets = 10
  val iterative = generateParenthesis.executeIterative(numberOfBrackets)
  val recursive = generateParenthesis.executeRecursive(GenerateParenthesis.Wrapper(numberOfBrackets, numberOfBrackets))
  val isEqual = iterative.containsAll(recursive) && recursive.containsAll(iterative)

  println("isEqual $isEqual size ${iterative.size}")
}