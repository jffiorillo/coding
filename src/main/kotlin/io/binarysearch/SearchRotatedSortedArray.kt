@file:Suppress("MemberVisibilityCanBePrivate")

package io.binarysearch


// https://leetcode.com/explore/learn/card/binary-search/125/template-i/952/

class SearchRotatedSortedArray {

  fun execute(input: IntArray, target: Int): Int = findPivot(input).let { pivot ->
    when {
      pivot < 0 -> execute(input, target, 0)
      input[pivot] == target -> pivot
      target < input[0] -> execute(input, target, pivot + 1)
      else -> execute(input, target, 0, pivot)
    }
  }

  fun execute(input: IntArray, target: Int, start: Int = 0, end: Int = input.size-1): Int = when {
    end < start -> -1
    start == end -> if (input[start] == target) start else -1
    else -> {
      val pivot = (end - start) / 2 + start
      val value = input[pivot]
      when {
        value == target -> pivot
        value < target -> execute(input, target, pivot + 1, end)
        else -> execute(input, target, start, pivot - 1)
      }
    }
  }

  fun findPivot(input: IntArray, start: Int = 0, end: Int = input.size): Int = ((end - start) / 2 + start).let { pivot ->
    when {
      // base cases
      end < start -> -1
      end == start -> start
      pivot < end && input[pivot] > input[pivot + 1] -> pivot
      pivot > start && input[pivot] < input[pivot - 1] -> pivot - 1
      input[start] >= input[pivot] -> findPivot(input, start, pivot - 1)
      else -> findPivot(input, pivot + 1, end)
    }
  }
}


fun main() {
  val search = SearchRotatedSortedArray()
  println(search.execute(intArrayOf(4,5,6,7,0,1,2), 3))
}