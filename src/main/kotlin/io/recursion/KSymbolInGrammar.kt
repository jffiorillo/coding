package io.recursion


//https://leetcode.com/explore/featured/card/recursion-i/253/conclusion/1675/
class KSymbolInGrammar {

  fun executeRecursive(N: Int, K: Int): Int = when {
    N == 1 -> 0
    K % 2 == 0 -> if (executeRecursive(N - 1, K / 2) == 0) 1 else 0
    else -> if (executeRecursive(N - 1, (K + 1) / 2) == 0) 0 else 1
  }
}

fun main() {
  val kSymbolInGrammar = KSymbolInGrammar()
  listOf(
      Pair(30, 434991989),
      Pair(8, 1)
  ).map { (row, index) -> println("${kSymbolInGrammar.executeRecursive(row, index)}") }
}