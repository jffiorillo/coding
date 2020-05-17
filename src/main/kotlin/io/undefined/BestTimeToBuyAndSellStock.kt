package io.undefined

import io.utils.runTests

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class BestTimeToBuyAndSellStock {

  fun execute(input: IntArray): Int {
    if (input.isEmpty()) return 0
    val maximums = run {
      var max = Int.MIN_VALUE
      input.indices.map { index -> maxOf(input[input.lastIndex - index], max).also { max = it } }.reversed()
    }
    val minimums = run {
      var min = Int.MAX_VALUE
      input.map { value -> minOf(min, value).also { min = it } }
    }
    var max = 0
    (1 until input.size).forEach { index ->
      max = maxOf(maximums[index] - minimums[index], max)
    }
    return max
  }

  // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
  fun maxProfit(prices: IntArray): Int {
    var minprice = Int.MAX_VALUE
    var maxprofit = 0
    for (i in prices.indices) {
      when {
        prices[i] < minprice -> minprice = prices[i]
        prices[i] - minprice > maxprofit -> maxprofit = prices[i] - minprice
      }
    }
    return maxprofit
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(7, 1, 5, 3, 6, 4) to 5
  )) { (input, value) -> value to BestTimeToBuyAndSellStock().execute(input) }
}