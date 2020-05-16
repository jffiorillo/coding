package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/accounts-merge/
class AccountMerge {


  fun execute(input: List<List<String>>): List<List<String>> {
    val information = input.mapIndexed { index, list -> index to (list.first() to list.subList(1, list.size).toMutableSet()) }.toMap().toMutableMap()
    val map = mutableListOf<Pair<MutableSet<String>, Int>>()
    val groups = information.map { (key, value) -> value.second to key }
    for ((set, index) in groups) {
      val keys = map.filter { (key, _) -> key.any { set.contains(it) } }
      when {
        keys.isEmpty() -> map.add(set to index)
        else -> {
          val newKey = keys.first()
          newKey.first.addAll(set)
          (1 until keys.size).map { keys[it] }.forEach { (values, index) ->
            newKey.first.addAll(values)
            information.remove(index)
          }
          information.remove(index)
        }
      }
    }
    return information.values.map { (name, emails) -> mutableListOf(name).apply { addAll(emails.sorted()) } }
  }


  private data class Node(val name: String, val value: Set<String>, val children: MutableList<Node> = mutableListOf())
}

fun main() {
  runTests(listOf(
      listOf(
          listOf("David", "David0@m.co", "David1@m.co"),
          listOf("David", "David3@m.co", "David4@m.co"),
          listOf("David", "David4@m.co", "David5@m.co"),
          listOf("David", "David2@m.co", "David3@m.co"),
          listOf("David", "David1@m.co", "David2@m.co"))
          to listOf(listOf("David", "David0@m.co", "David1@m.co", "David2@m.co", "David3@m.co", "David4@m.co", "David5@m.co"))
  )) { (input, value) -> value to AccountMerge().execute(input) }
}