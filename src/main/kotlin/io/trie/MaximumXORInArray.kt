package io.trie

import kotlin.math.pow


// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1057/
class MaximumXORInArray {

  fun executeInefficient(nums: IntArray): Int =
      nums.foldIndexed(0) { index, acc, value ->
        maxOf(acc, (index + 1 until nums.size).map { j -> nums[j].xor(value) }.maxOrNull() ?: Integer.MIN_VALUE)
      }

  internal data class TrieNode(var left: TrieNode? = null, var right: TrieNode? = null) {

    // O(1) TC
    fun insert(num: Int) {
      // insert the binary representation of the num
      var current = this
      for (i in 31 downTo 0) {
        //get the ith bit
        val bit = num shr i and 1
        current = if (bit == 0) {
           current.left ?: TrieNode().also { current.left = it }
        } else {
          current.right ?: TrieNode().also { current.right = it }
        }
      }
    }

    // O(1) TC
    fun find(num: Int): Int {
      // we always want to go to opposite path (if exists) because in that way we can have highly varying bits
      var current = this
      var opp = 0
      for (i in 31 downTo 0) {
        val bit = num shr i and 1
        current = (if (bit == 0) {
          current.right?.also { opp += 2.0.pow(i.toDouble()).toInt() } ?: current.left ?: break
        } else {
          current.left ?: current.right?.also { opp += 2.0.pow(i.toDouble()).toInt() } ?: break
        })
      }
      return opp
    }
  }

  fun execute(nums: IntArray): Int = TrieNode().let { root ->
    nums.forEach { root.insert(it) }
    nums.fold(0) { acc, value -> acc.coerceAtLeast(root.find(value) xor value) }
  }
}

fun main() {
  val maximumXORInArray = MaximumXORInArray()
  println("${maximumXORInArray.execute(intArrayOf(2, 4, 5, 25))}")
}