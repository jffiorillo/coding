package io.tree

import io.utils.runTests

// https://leetcode.com/problems/coin-change/
class CoinChange {

  fun execute(coins: IntArray, amountLeft: Int, count: IntArray = IntArray(amountLeft) { 0 }): Int = when {
    amountLeft < 0 -> -1
    amountLeft == 0 -> 0
    count[amountLeft - 1] != 0 -> count[amountLeft - 1]
    else -> {
      val min = coins.fold(Int.MAX_VALUE) { acc, coin ->
        val res = execute(coins, amountLeft - coin, count)
        if (res in 0 until acc) 1 + res else acc
      }
      count[amountLeft - 1] = if (min == Int.MAX_VALUE) -1 else min
      count[amountLeft - 1]
    }
  }
}

fun main() {
  val coinChange = CoinChange()
  runTests(listOf(
      Triple(intArrayOf(1, 2, 5), 9, 3),
      Triple(intArrayOf(1, 2, 5), 100, 20),
      Triple(intArrayOf(1, 2, 5), 103, 22),
      Triple(intArrayOf(1, Int.MAX_VALUE), 2, 2),
      Triple(intArrayOf(186, 419, 83, 408), 6249, 20)
  ))
  { (coins, amount, value) -> coinChange.execute(coins, amount).let { Triple(it == value, value, it) } }
}