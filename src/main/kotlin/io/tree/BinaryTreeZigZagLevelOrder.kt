@file:Suppress("unused")

package io.tree

import io.models.TreeNode

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
class BinaryTreeZigZagLevelOrder {

  fun execute(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()
    var stack: List<TreeNode> = listOf(root)
    val result = mutableListOf<List<Int>>()
    var zigzag = true
    while (stack.isNotEmpty()) {
      if (zigzag) result.add(stack.map { it.`val` })
      else result.add(stack.reversed().map { it.`val` })
      stack = stack.flatMap {
        when {
          it.right == null && it.left == null -> emptyList()
          it.right == null -> listOf(it.left!!)
          it.left == null -> listOf(it.right!!)
          else -> listOf(it.left!!, it.right!!)
        }
      }
      zigzag = !zigzag
    }
    return result
  }
}