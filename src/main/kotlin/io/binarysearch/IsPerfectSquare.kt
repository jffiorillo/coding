package io.binarysearch

import kotlin.math.sqrt

// https://leetcode.com/explore/learn/card/binary-search/137/conclusion/978/
class IsPerfectSquare {

  fun execute(input: Int): Boolean = when (input) {
    0, 1 -> true
    Int.MAX_VALUE -> false
    else -> {
      var start = 0
      var end = sqrt(Int.MAX_VALUE.toDouble()).toInt()
      var result = false
      while (start <= end && !result) {
        val pivot = start + (end - start) / 2
        val value = pivot*pivot
        println("$start $end $pivot $value")
        when {
          value == input -> {
            result = true
          }
          value < input -> {
            start = pivot + 1
          }
          else -> {
            end = pivot - 1
          }
        }
      }
      result
    }
  }
}

fun main(){
  val isPerfectSquare = IsPerfectSquare()
  listOf(
//      808201 to true,
//      2147483647 to false,
      2147395600 to true
  ).map { (input,result) ->
    val output = isPerfectSquare.execute(input)
    println(output)
    println(sqrt(input.toDouble()))
  }
}