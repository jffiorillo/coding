package io.queue

import java.util.*
import kotlin.math.pow

// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1371/
class PerfectSquares {

  fun execute(input: Int): Int {

    val stack = Stack<List<Int>>()
    var steps = 1
    stack.add(listOf(input))
    val perfectNumbers = generatePerfectSquareNumbers(input)
    while (stack.isNotEmpty()) {
      stack.pop().flatMap { current ->
        perfectNumbers.map { squareNumber ->
          when {
            current - squareNumber == 0 -> return steps
            current - squareNumber < 0 -> null
            else -> current - squareNumber
          }
        }.filterNotNull()
      }.let { newGeneration ->
        if (newGeneration.isNotEmpty()) stack.push(newGeneration)
      }
      steps += 1
    }
    return -1
  }

  private fun generatePerfectSquareNumbers(maximum: Int) = (1..maximum).filter { number ->
    (1..number).firstOrNull { it.toDouble().pow(2).toInt() == number } != null
  }
}


private data class NumbersWrapper(val value: Int, val values: List<Int>) {
  fun generateNewValue(minus: Int) = NumbersWrapper(value - minus, values + minus)
}

fun main() {
  val perfectSquares = PerfectSquares()
  println("${perfectSquares.execute(496)}")
}
