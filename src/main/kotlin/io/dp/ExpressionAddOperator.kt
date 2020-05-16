package io.dp

import io.utils.runTests
import java.util.*
import java.util.function.Consumer


// https://leetcode.com/problems/expression-add-operators/
class ExpressionAddOperator {

  fun execute(input: String, target: Int): List<String> =
      helperRecursion(input).filter { evaluate(it) == target }

  private fun evaluate(input: String, i: Int = input.lastIndex): Int? {
    var accum = 0
    var index = i
    loop@ while (index in input.indices) {
      when (input[index]) {
        '+' -> return evaluate(input, index - 1)?.let { it + accum }
        '-' -> return evaluate(input, index - 1)?.let { it - accum }
        '*' -> {
          readInteger(input, index - 1)?.let { (number, newIndex) ->
            accum *= number
            index = newIndex
          } ?: return null
        }
        else -> {
          readInteger(input, index)?.let { (number, newIndex) ->
            accum = number
            index = newIndex
          } ?: return null
        }
      }
    }
    return accum
  }

  private fun readInteger(input: String, index: Int): Pair<Int, Int>? {
    var accum = ""
    var currentIndex = index
    while (currentIndex >= 0 && input[currentIndex].isDigit()) {
      accum = input[currentIndex] + accum
      currentIndex--
    }
    return accum.toIntOrNull()?.let { it to currentIndex }
  }

  private fun helperRecursion(input: String, index: Int = 0, dp: MutableMap<Int, List<String>> = mutableMapOf()): List<String> {
    if (input.isEmpty()) return emptyList()
    if (dp.contains(index)) return dp.getValue(index)
    if (index == input.lastIndex) return listOf(input[index].toString()).also { dp[index] = it }

    val value = input[index].toString().toInt()
    return helperRecursion(input, index + 1, dp)
        .flatMap { result ->
          when (value) {
            0 -> listOf(sum(result, value), minus(result, value), plus(result, value))
            else -> listOf(sum(result, value), minus(result, value), add(result, value), plus(result, value))
          }
        }
        .also { dp[index] = it }
  }

  fun sum(accum: String, value: Int) = "$value+$accum"
  fun minus(accum: String, value: Int) = "$value-$accum"
  fun add(accum: String, value: Int) = "$value$accum"
  fun plus(accum: String, value: Int): String = "$value*$accum"

  // https://leetcode.com/problems/expression-add-operators/solution/
  lateinit var answer: ArrayList<String>
  var digits: String? = null
  var target: Long = 0
  fun recurse(
      index: Int,
      previousOperand: Long,
      operand: Long,
      value: Long,
      ops: ArrayList<String>) {
    var currentOperand = operand
    val nums = digits

    // Done processing all the digits in num
    if (index == nums!!.length) {

      // If the final value == target expected AND
      // no operand is left unprocessed
      if (value == target && currentOperand == 0L) {
        val sb = StringBuilder()
        ops.subList(1, ops.size).forEach(Consumer { v: String? -> sb.append(v) })
        answer.add(sb.toString())
      }
      return
    }

    // Extending the current operand by one digit
    currentOperand = currentOperand * 10 + Character.getNumericValue(nums[index])
    val currentValRep = java.lang.Long.toString(currentOperand)
    val length = nums.length

    // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
    // valid operand. Hence this check
    if (currentOperand > 0) {

      // NO OP recursion
      recurse(index + 1, previousOperand, currentOperand, value, ops)
    }

    // ADDITION
    ops.add("+")
    ops.add(currentValRep)
    recurse(index + 1, currentOperand, 0, value + currentOperand, ops)
    ops.removeAt(ops.size - 1)
    ops.removeAt(ops.size - 1)
    if (ops.size > 0) {

      // SUBTRACTION
      ops.add("-")
      ops.add(currentValRep)
      recurse(index + 1, -currentOperand, 0, value - currentOperand, ops)
      ops.removeAt(ops.size - 1)
      ops.removeAt(ops.size - 1)

      // MULTIPLICATION
      ops.add("*")
      ops.add(currentValRep)
      recurse(
          index + 1,
          currentOperand * previousOperand,
          0,
          value - previousOperand + currentOperand * previousOperand,
          ops)
      ops.removeAt(ops.size - 1)
      ops.removeAt(ops.size - 1)
    }
  }
  fun addOperators(num: String, target: Int): List<String>? {
    if (num.isEmpty()) return ArrayList()
    this.target = target.toLong()
    digits = num
    answer = ArrayList()
    recurse(0, 0, 0, 0, ArrayList())
    return answer
  }
}

fun main() {
  runTests(listOf(
      Triple("123", 6, setOf("1*2*3", "1+2+3")),
      Triple("105", 5, setOf("10-5", "1*0+5")),
      Triple("232", 8, setOf("2*3+2", "2+3*2")),
      Triple("3456237490", 9191, setOf())
  )) { (input, target, value) ->
    value to ExpressionAddOperator().execute(input, target).toSet()
  }
}