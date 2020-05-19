package io.tree

import io.models.TreeNode
import java.util.*

// https://leetcode.com/problems/range-sum-of-bst/
class RangeSumOfBinarySearchTree {

  fun execute(input: TreeNode?, left: Int, right: Int): Int {
    if (input == null) return 0
    val stack = LinkedList<TreeNode>().apply { add(input) }
    var result = 0
    while (stack.isNotEmpty()) {
      val node = stack.pop()
      val value = node.`val`
      when {
        value in left..right -> {
          result += value
          node.left?.let { stack.add(it) }
          node.right?.let { stack.add(it) }
        }
        value < left -> node.right?.let { stack.add(it) }
        value > right -> node.left?.let { stack.add(it) }
      }
    }
    return result
  }
}