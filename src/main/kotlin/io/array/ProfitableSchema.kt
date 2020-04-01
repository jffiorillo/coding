@file:Suppress("ArrayInDataClass")

package io.array

// https://leetcode.com/problems/profitable-schemes/
class ProfitableSchema {
  fun profitableSchemes(numberOfPeople: Int, minimumProfitable: Int, group: IntArray, profit: IntArray): Int =
      execute(numberOfPeople, minimumProfitable, group, profit).size
  fun execute(numberOfPeople: Int, minimumProfitable: Int, group: IntArray, profit: IntArray) =
      profit.powerSet(numberOfPeople, minimumProfitable, group)
}

fun IntArray.powerSet(
    numberOfPeople: Int,
    minimumProfitable: Int,
    group: IntArray,
    index: Int = -1,
    current: List<Int> = listOf(), solution: MutableSet<List<Int>> = mutableSetOf()): Set<List<Int>> = when {
  index == size || current.isNotEmpty() && current.map { group[it] }.reduce { acc, value -> acc + value } > numberOfPeople-> solution
  else -> solution.also {
    solution.add(current)
    // Try appending remaining characters
    // to current subset
    for (i in index + 1 until size) {
      solution.addAll(this.powerSet(numberOfPeople, minimumProfitable, group, i, current + i)
      )
    }
  }.filter { element ->
    element.isNotEmpty()
        && element.map { this[it] }.reduce { acc, value -> acc + value } >= minimumProfitable
        && element.map { group[it] }.reduce { acc, value -> acc + value } <= numberOfPeople
  }.toSet()


}

private data class Data(
    val numberOfPeople: Int,
    val minimumProfitable: Int,
    val group: List<Int>,
    val profit: List<Int>,
    val result: Int)

fun main() {
  val profitableSchema = ProfitableSchema()
  listOf(
      Data(5, 3, listOf(2, 2), listOf(2, 3), 2),
      Data(10, 5, listOf(2, 3, 5), listOf(6, 7, 8), 7),
      Data(10, 100,
              listOf(8,8,7,4,3,1,1,6,11,3,1,7,6,9,9,1,8,9,3,10,10,8,7,6,9,10,6,2,2,6,9,7,5,6,2,1,2,10,11,6,8,9,9,8,11,6,2,2,4,5,1,2,1,11,3,2,11,7,11,4,5,7,6,9,6,7,10,10,9,10,10,8,8,6,9,8,5,1,2,5,10,1,4,2,1,5,1,3,6,6,10,6,2,3,2,1,9,6,6,4),
              listOf(23,36,94,35,73,7,65,25,22,4,62,62,12,18,89,62,2,66,85,94,73,31,56,95,71,91,53,75,100,47,68,4,64,52,97,8,52,32,98,64,2,64,33,21,52,44,41,50,59,40,48,47,39,9,100,1,43,94,63,23,21,92,36,69,100,8,75,16,79,98,72,83,70,11,3,41,91,18,17,76,71,58,71,62,34,49,58,59,90,84,12,43,27,60,47,89,31,14,11,15),
      3)
  ).forEachIndexed { index, (numberOfPeople, minimumProfitable, group, profit, value) ->
    val output = profitableSchema.execute(numberOfPeople, minimumProfitable, group.toIntArray(), profit.toIntArray())
    if (output.size == value)
      println("index $index output $output is valid")
    else
      println("index $index Except $value but instead got ${output.size}")
  }
  println("INCOMPLETE SOLUTION, WE NEED DYNAMIC PROGRAMMING")

}