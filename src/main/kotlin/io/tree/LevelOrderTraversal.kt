package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
class LevelOrderTraversal {

  fun execute(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()
    var stack = listOf(root)
    val result = mutableListOf<List<Int>>()
    while (stack.isNotEmpty()) {
      result.add(0, stack.map { it.`val` })
      stack = stack.filter { it.right != null || it.left != null }.flatMap { listOfNotNull(it.left, it.right) }
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      TreeNode(3,
          TreeNode(9),
          TreeNode(20,
              TreeNode(15),
              TreeNode(17))) to
          listOf(listOf(15, 17), listOf(9, 20), listOf(3))
  )) { (input, value) -> value to LevelOrderTraversal().execute(input) }
}