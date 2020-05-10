package io.tree

import io.models.TreeNode

// https://leetcode.com/problems/binary-tree-right-side-view/
class BinaryTreeRightSideView {

  fun execute(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    var stack = listOf(root)
    val result = mutableListOf<Int>()
    while (stack.isNotEmpty()) {
      result.add(stack.last().`val`)
      stack = stack.flatMap { node ->
        when {
          node.left != null && node.right != null -> listOf(node.left!!, node.right!!)
          node.left != null -> listOf(node.left!!)
          node.right != null -> listOf(node.right!!)
          else -> emptyList()
        }
      }
    }
    return result
  }
}