package io.array


// https://leetcode.com/problems/maximum-product-of-three-numbers/
class MaxNumberProductThreeNumbers {

  fun execute(input: IntArray): Int =
      maxOf(
          findBiggest(input, 3).reduce { acc, value -> acc * value },
          findSmallest(input, 2).reduce { acc, value -> acc * value } * findBiggest(input, 1).first())

  private fun findBiggest(input: IntArray, numbers: Int, founds: MutableList<Int> = mutableListOf()) =
      find(input, numbers, founds) { acc, value -> value?.let { it < acc } ?: true }

  private fun findSmallest(input: IntArray, numbers: Int, founds: MutableList<Int> = mutableListOf()) =
      find(input, numbers, founds) { acc, value -> value?.let { it > acc } ?: true }

  private fun find(input: IntArray, numbers: Int, founds: MutableList<Int> = mutableListOf(), condition: (Int, Int?) -> Boolean): List<Int> =
      (0 until numbers).map {
        input.foldIndexed(null as Pair<Int, Int>?) { index, acc, value ->
          if (condition(value, acc?.second) && founds.none { i -> i == index }) index to value else acc
        }?.also { founds.add(it.first) }
      }.map { it!!.second }
}

fun main() {
  val maxNumberProductThreeNumbers = MaxNumberProductThreeNumbers()
  listOf(
      intArrayOf(1, 2, 3) to 6
  ).forEachIndexed { index, (input, value) ->
    val output = maxNumberProductThreeNumbers.execute(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}