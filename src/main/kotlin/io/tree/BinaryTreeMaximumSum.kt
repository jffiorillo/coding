package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/532/week-5/3314/
// https://leetcode.com/problems/binary-tree-maximum-path-sum/
class BinaryTreeMaximumSum {

  var maxSum = Int.MIN_VALUE
  fun maxPathSum(root: TreeNode?): Int {
    maxSum = Int.MIN_VALUE
    execute(root)
    return maxSum
  }

  fun execute(root: TreeNode?): Int = when (root) {
    null -> 0
    else -> {
      val current = root.`val`
      val left = maxOf(root.left?.let { execute(it) } ?: 0, 0)
      val right = maxOf(root.right?.let { execute(it) } ?: 0, 0)
      maxSum = maxOf(maxSum, left + right + current)
      maxOf(left, right) + current
    }
  }
}

fun main() {
  runTests(listOf(
      TreeNode(1, TreeNode(2), TreeNode(3)) to 6,
      TreeNode(-10,
          left = TreeNode(9),
          right = TreeNode(20, TreeNode(15), TreeNode(7))) to 42,
      TreeNode(-3) to -3,
      TreeNode(1, TreeNode(2)) to 3,
      TreeNode(1, TreeNode(-2), TreeNode(3)) to 4,
      TreeNode(1,
          TreeNode(-2,
              TreeNode(1, TreeNode(-1)),
              TreeNode(3)),
          TreeNode(-3, TreeNode(-2))) to 3,
      TreeNode(2, TreeNode(-1)) to 2
  )) { (input, value) -> value to BinaryTreeMaximumSum().maxPathSum(input) }
}