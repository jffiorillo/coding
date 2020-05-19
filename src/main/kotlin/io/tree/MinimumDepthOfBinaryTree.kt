package io.tree

import io.models.TreeNode

// https://leetcode.com/problems/minimum-depth-of-binary-tree/
class MinimumDepthOfBinaryTree {

  fun execute(input: TreeNode?): Int {
    if (input == null) return 0
    var result = 1
    var stack = listOf(input)
    while (stack.isNotEmpty()) {
      if (stack.any { it.left == null && it.right == null })
        break
      stack = stack.flatMap { node ->
        when {
          node.left == null -> listOf(node.right!!)
          node.right == null -> listOf(node.left!!)
          else -> listOf(node.left!!, node.right!!)
        }
      }
      result++
    }
    return result
  }
}