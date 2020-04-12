package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3293/
class DiameterBinaryTree {

  fun execute(root: TreeNode?): Int {
    var stack = root?.let { listOf(it) } ?: emptyList()
    var maxSize = 0
    while (stack.isNotEmpty()) {
      maxSize = stack.fold(maxSize) { acc, node -> maxOf(acc, lengthDFS(node.right) + lengthDFS(node.left)) }
      stack = stack.flatMap { listOf(it.left, it.right) }.filterNotNull()
    }
    return maxSize
  }


  private fun lengthDFS(root: TreeNode?, length: Int = 0): Int = when (root) {
    null -> length
    else -> maxOf(lengthDFS(root.left, length + 1), lengthDFS(root.right, length + 1))
  }
}

fun main() {
  runTests(listOf(
      TreeNode(1,
          left = TreeNode(2,
              left = TreeNode(4),
              right = TreeNode(5)),
          right = TreeNode(3))
          to 3
  )) { (input, value) -> value to DiameterBinaryTree().execute(input) }
}