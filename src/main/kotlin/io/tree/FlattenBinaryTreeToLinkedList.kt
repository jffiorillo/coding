package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
class FlattenBinaryTreeToLinkedList {

  fun execute(root: TreeNode?): Pair<TreeNode, TreeNode>? = when (root) {
    null -> null
    else -> {
      val (rFirst, rLast) = execute(root.right) ?: null to null
      val lLast = execute(root.left)?.let { (lFirst, lRight) ->
        root.right = lFirst
        lRight.apply { right = rFirst }
      }
      root.left = null
      root to (rLast ?: lLast ?: root)
    }
  }
}

fun main() {
  runTests(listOf(
      TreeNode(1, TreeNode(2, TreeNode(3), TreeNode(4)), TreeNode(5, right = TreeNode(6))) to
          TreeNode(1, right = TreeNode(2, right = TreeNode(3, right = TreeNode(4, right = TreeNode(5, right = TreeNode(6))))))
  )) { (input, value) -> value to FlattenBinaryTreeToLinkedList().execute(input)?.first }
}