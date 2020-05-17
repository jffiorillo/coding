package io.intervals

import io.utils.runTests
import java.util.*
import kotlin.Comparator


// https://leetcode.com/problems/meeting-rooms-ii/
class MeetingRoomII {


  fun execute(input: Array<IntArray>): Int {
    if (input.isEmpty()) return 0
    val allocator = PriorityQueue(input.size, Comparator<Int> { a, b -> a - b })
    input.sortBy { it.first() }
    // Add the first meeting
    allocator.add(input.first().last())
    // Iterate over remaining intervals
    for (index in 1 until input.size) {

      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (input[index].first() >= allocator.peek()) {
        allocator.poll()
      }

      // If a new room is to be assigned, then also we add to the heap,
      // If an old room is allocated, then also we have to add to the heap with updated end time.
      allocator.add(input[index].last())
    }
    // The size of the heap tells us the minimum rooms required for all the meetings.
    return allocator.size
  }

  fun execute1(input: Array<IntArray>): Int {
    if (input.isEmpty()) return 0
    var result = 1
    input.sortBy { it[0] }

    for (index in input.indices) {
      result = maxOf(
          checkPrevious(input, index, input[index].first()) + checkNext(input, index, input[index].first()) + 1,
          checkPrevious(input, index, input[index].last(), 1) + checkNext(input, index, input[index].last(), 1) + 1,
          result)
      if (result == input.size) break
    }
    return result
  }

  private fun checkNext(input: Array<IntArray>, currentIndex: Int, value: Int, extra: Int = 0): Int {
    var index = currentIndex + 1
    var result = 0
    while (index in input.indices) {
      if (value in input[index].first() + extra until input[index].last())
        result++
      index++
    }
    return result
  }

  private fun checkPrevious(input: Array<IntArray>, currentIndex: Int, value: Int, extra: Int = 0): Int {
    var index = currentIndex - 1
    var result = 0
    while (index in input.indices) {
      if (value in input[index].first() + extra until input[index].last())
        result++
      index--
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20)) to 2,
      arrayOf(intArrayOf(13, 15), intArrayOf(1, 13)) to 1,
      arrayOf(intArrayOf(4, 18), intArrayOf(1, 35), intArrayOf(12, 45), intArrayOf(25, 46), intArrayOf(22, 27)) to 4
  )) { (input, value) -> value to MeetingRoomII().execute(input) }
}