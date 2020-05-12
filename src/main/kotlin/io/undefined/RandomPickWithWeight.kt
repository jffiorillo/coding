package io.undefined

import kotlin.random.Random

// https://leetcode.com/problems/random-pick-with-weight/
class RandomPickWithWeight(private val w: IntArray) {

  private val max = w.sum()
  private val random = Random.Default

  fun pickIndex(): Int = random.nextInt(max).let { random ->
    w.foldIndexed(0 to 0) { index, (accValue, accIndex), value ->
      when {
        random in accValue until accValue + value -> accValue + value to index
        random >= accValue + value -> accValue + value to accIndex
        else -> accValue to accIndex
      }
    }.second
  }
}

fun main() {
  RandomPickWithWeight(intArrayOf(4, 2)).pickIndex()
}