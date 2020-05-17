package io.intervals

// https://leetcode.com/problems/meeting-rooms/
class MeetingRoom {

  fun execute(input: Array<IntArray>): Boolean = input.sortedBy { it.first() }.let { sorted ->
    sorted.foldIndexed(true) { index, acc, value ->
      when {
        input.lastIndex == index -> acc
        sorted[index + 1].first() < value.last() -> return false
        else -> {
          true
        }
      }
    }
  }
}