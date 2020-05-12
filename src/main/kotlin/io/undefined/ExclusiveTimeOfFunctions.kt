package io.undefined

import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/exclusive-time-of-functions/
class ExclusiveTimeOfFunctions {

  // https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
  fun execute(n: Int, logs: List<String>): IntArray {
    val logsTriple = logs.toTriple()
    val result = IntArray(n)
    val stack = LinkedList<Int>()
    var previousTime = 0
    logsTriple.forEach { (id, action, timestamp) ->
      if (stack.isNotEmpty()) result[stack.peek()] += timestamp - previousTime
      previousTime = timestamp
      when (action) {
        "start" -> stack.push(id)
        "end" -> result[stack.pop()]++.also { previousTime++ }
      }
    }
    return result
  }

  private fun List<String>.toTriple(): List<Triple<Int, String, Int>> =
      this.map { it.split(':').let { (id, action, timestamp) -> Triple(id.toInt(), action, timestamp.toInt()) } }
}

fun main() {
  runTests(listOf(
      Triple(2, listOf("0:start:0", "1:start:2", "1:end:5", "0:end:6"), listOf(3, 4)),
      Triple(1, listOf("0:start:0", "0:start:1", "0:start:2", "0:end:3", "0:end:4", "0:end:5"), listOf(6))
  )) { (n, logs, value) -> value to ExclusiveTimeOfFunctions().execute(n, logs).toList() }
}