package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/problems/binary-tree-vertical-order-traversal/
class BinaryTreeVerticalOrderTraversal {


  fun execute(root: TreeNode?): List<List<Int>> =
      executeRecursion(root).first.entries.sortedBy { it.key }.map { (_, value) -> value.sortedBy { it.first }.map { it.second } }

  private fun executeRecursion(
      root: TreeNode?,
      value: Int? = null,
      acc: MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf(),
      size: Int = 0
  ): Pair<MutableMap<Int, MutableList<Pair<Int, Int>>>, Int> {
    if (root == null) return acc to (value ?: 0)
    var currentValue = value ?: 0

    if (root.left != null)
      currentValue = executeRecursion(root.left, value?.let { it - 1 }, acc, size + 1).second + 1
    acc.getOrPut(currentValue) { mutableListOf() }.add(size to root.`val`)
    if (root.right != null)
      executeRecursion(root.right, currentValue + 1, acc, size + 1)
    return acc to currentValue
  }
}


fun main() {
  @Suppress("RemoveExplicitTypeArguments")
  runTests(listOf(
      TreeNode(3, TreeNode(9),
          TreeNode(20, TreeNode(15), TreeNode(7))) to listOf(listOf(9), listOf(3, 15), listOf(20), listOf(7)),
      null as TreeNode? to listOf<List<Int>>(),
      TreeNode(1,
          right = TreeNode(4, TreeNode(3, TreeNode(2)), TreeNode(5, right = TreeNode(6)))) to listOf(listOf(2), listOf(1, 3), listOf(4), listOf(5), listOf(6)
      )
  )) { (input, value) -> value to BinaryTreeVerticalOrderTraversal().execute(input) }
}