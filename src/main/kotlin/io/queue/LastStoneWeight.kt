package io.queue

import io.models.TreeNode
import io.utils.runTests
import java.util.*

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3297/
class LastStoneWeight {

  fun execute(stones: IntArray): Int {
    val stack = LinkedList(stones.toList())
    stack.sortDescending()
    while (stack.size > 1) {
      val (first, second) = stack.pop() to stack.pop()
      if (first != second) {
        stack.add(first - second)
        stack.sortDescending()
      }
    }
    return if (stack.size == 1) stack.pop() else 0
  }

  fun executeTreeNode(stones: IntArray): Int {
    var root: TreeNode? = stones.toBinarySortTree()
    while (root != null) {
      val (newRoot, biggestValue) = root.generateRootAndGetBiggestValue()
      if (newRoot == null) {
        return biggestValue
      }
      val (newRoot2, biggestValue2) = newRoot.generateRootAndGetBiggestValue()
      if (newRoot2 == null) {
        return biggestValue - biggestValue2
      }
      root = newRoot2
      if (biggestValue != biggestValue2)
        root.addValue(biggestValue - biggestValue2)
    }
    return 0
  }


  private fun IntArray.toBinarySortTree(): TreeNode? =
      fold(null as TreeNode?) { acc, value -> acc?.apply { addValue(value) } ?: TreeNode(value) }

  private fun TreeNode.generateRootAndGetBiggestValue(): Pair<TreeNode?, Int> =
      this.popBiggestValue()?.let { this to it } ?: left to this.`val`

  private fun TreeNode.popBiggestValue(): Int? {
    var current = this
    while (current.right?.right != null) {
      current = current.right!!
    }
    return current.right?.let { right ->
      current.right = right.left
      right.`val`
    }
  }

  private fun TreeNode.addValue(value: Int) {
    var current = this
    loop@ while (true) {
      when {
        value < current.`val` && current.left != null -> {
          current = current.left!!
        }
        value < current.`val` && current.left == null -> {
          current.left = TreeNode(value)
          break@loop
        }
        value > current.`val` && current.right != null -> {
          current = current.right!!
        }
        else -> {
          current.right = TreeNode(value, right = current.right)
          break@loop
        }
      }
    }
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(2, 7, 4, 1, 8, 1) to 1
  )) { (input, value) -> value to LastStoneWeight().executeTreeNode(input) }
}