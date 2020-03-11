package io.recursion

//https://leetcode.com/explore/featured/card/recursion-i/255/recursion-memoization/1662/
class ClimbingStairs {

  private val undefined = Integer.MAX_VALUE

  fun execute(stairs: Int, memoization: HashMap<Int, Int> = hashMapOf<Int, Int>().also {
    (0..stairs).map { index -> it[index] = undefined }
  }): Int = when {
    stairs <= 0 -> 0
    stairs == 1 || stairs == 2 -> stairs
    memoization[stairs] != undefined -> memoization.getValue(stairs)
    else ->
      (execute(stairs - 1, memoization).also { value -> memoization[stairs - 1] = value } +
          execute(stairs - 2, memoization).also { value -> memoization[stairs - 2] = value })
          .also { value -> memoization[stairs] = value }
  }
}

fun main() {
  val climbingStairs = ClimbingStairs()
  listOf(
//      2,
//      3,
      38
  ).map {
    println("For $it output is ${climbingStairs.execute(it)}")
  }
}


