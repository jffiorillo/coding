package io.hash

// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1117/
class AreIsomorphicStrings {

  fun execute(input0 :String, input1: String): Boolean {
    val input0Indexed = input0.mapIndexed { index, char -> index to char }
    val group0 = input0.toSet().map { char -> input0Indexed.filter { it.second == char }.map { it.first } }
    val input1Indexed = input1.mapIndexed { index, char -> index to char }
    val group1 = input1.toSet().map { char -> input1Indexed.filter { it.second == char }.map { it.first } }
    return group0 == group1
  }
}