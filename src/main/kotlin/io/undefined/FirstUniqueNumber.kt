package io.undefined

import java.util.*

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3313/
class FirstUniqueNumber(input: IntArray) {

  private val uniqueNumber: Queue<Int> = LinkedList()
  private val duplicates: MutableMap<Int,Boolean> = mutableMapOf()

  init {
    input.forEach { add(it) }
  }

  fun showFirstUnique(): Int = uniqueNumber.firstOrNull() ?: -1

  fun add(value: Int) {
    when {
      duplicates[value] == null -> {
        uniqueNumber.add(value)
        duplicates[value] = false
      }
      duplicates[value] == false ->{
        uniqueNumber.remove(value)
        duplicates[value] = true
      }
    }
  }
}

fun main() {

  val firstUniqueNumber = FirstUniqueNumber(intArrayOf(1,2,3))
//  firstUniqueNumber.add(1)
  firstUniqueNumber.add(28)
  firstUniqueNumber.add(346)
  firstUniqueNumber.add(268)
  firstUniqueNumber.add(927)
  println(firstUniqueNumber.showFirstUnique())
}