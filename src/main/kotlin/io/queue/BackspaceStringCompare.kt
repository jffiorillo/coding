package io.queue

import io.utils.runTests
import java.util.*

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3291/
class BackspaceStringCompare {

  fun executeWithExtraSpace(input0: String, input1: String) = generateStack(input0) == generateStack(input1)


  private fun generateStack(input: String) = LinkedList<Char>().apply {
    input.forEach { char ->
      when {
        char == '#' && isEmpty() -> {
        }
        char == '#' -> pop()
        else -> push(char)
      }
    }
  }

  fun execute(input0: String, input1: String): Boolean {
    var i0 = input0.length - 1
    var i1 = input1.length - 1
    while (i0 >= 0 || i1 >= 0) {
      i0 = getNextIndex(input0, i0)
      i1 = getNextIndex(input1, i1)
      if ((i0 >= 0 != i1 >= 0) || (i0 >= 0 && i1 >= 0 && input0[i0] != input1[i1]))
        return false
      i0--
      i1--
    }
    return true
  }

  private fun getNextIndex(input: String, index: Int): Int {
    var currentIndex = index
    var skip = 0
    loop@ while (currentIndex >= 0) {
      when {
        input[currentIndex] == '#' -> {
          skip++
          currentIndex--
        }
        skip > 0 -> {
          skip--
          currentIndex--
        }
        else -> break@loop
      }
    }
    return currentIndex
  }
}

fun main() {
  runTests(listOf(
      Triple("bbbextm", "bbb#extm", false),
      Triple("nzp#o#g","b#nzp#o#g", true)
  )) { (input0, input1, value) -> value to BackspaceStringCompare().execute(input0, input1) }
}