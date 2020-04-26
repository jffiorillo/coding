package io.math

import io.utils.runTests

class SumOf4Numbers {

  fun execute(input: IntArray, target: Int): List<List<Int>> {
    val triples = (input.indices).flatMap { i -> (i + 1 until input.size).map { Triple(i, it, input[i] + input[it]) } }
    val map = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    triples.forEach { (i, j, sum) ->
      map.getOrPut(sum) { mutableListOf() }.add(i to j)
    }
    return triples.flatMap { (i, j, sum) ->
      map[target - sum]?.filter { (k, l) -> i != k && i != l && j != l && j != k }?.map { (k, l) ->
        listOf(input[i], input[j], input[k], input[l]).sorted()
      }?.distinct()
          ?: emptyList()
    }.distinct()
  }
}

fun main() {
  runTests(listOf(
      Triple(intArrayOf(1, 0, -1, 0, -2, 2), 0, listOf(
          listOf(-1, 0, 0, 1),
          listOf(-2, -1, 1, 2),
          listOf(-2, 0, 0, 2))),
      Triple(intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3), 0, listOf(
          listOf(-3, -2, 2, 3),
          listOf(-3, -1, 1, 3),
          listOf(-3, 0, 0, 3),
          listOf(-3, 0, 1, 2),
          listOf(-2, -1, 0, 3),
          listOf(-2, -1, 1, 2),
          listOf(-2, 0, 0, 2),
          listOf(-1, 0, 0, 1)
      )),
      Triple(intArrayOf(-5, 5, 4, -3, 0, 0, 4, -2), 4, listOf(
          listOf(-5, 0, 4, 5),
          listOf(-3, -2, 4, 5)
      ))
  )
//      ,outputString = { it.joinToString(separator = "\n") { item -> item.toString() } }
  ) { (input, target, value) -> value.map { it.sorted() } to SumOf4Numbers().execute(input, target).map { it.sorted() } }
}