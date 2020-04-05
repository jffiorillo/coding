package io.math

import io.utils.runTests

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
class BestTimeSellStockII {

  fun execute(prices: IntArray): Int {
    var current : Int? = null
    var profit = 0
    (0 until prices.size - 1).forEach { index ->
      when {

        current?.let { it < prices[index] } ?: false && prices[index + 1] < prices[index] -> {
          profit += prices[index] - current!!
          current = null
        }
        current == null && prices[index] < prices[index+1] -> {
          current = prices[index]
        }
      }
    }
    current?.let { profit += prices.last() - it }
    return profit
  }
}

fun main(){
  runTests(listOf(
      intArrayOf(1,2,3,4,5) to 4,
      intArrayOf(2,1,2,0,1) to 2,
      intArrayOf(7,1,5,3,6,4) to 7
  )){ (input,value) -> value to BestTimeSellStockII().execute(input) }
}