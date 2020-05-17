package io.tree

import io.models.TreeNode
import io.utils.runTests
import kotlin.math.absoluteValue

// https://leetcode.com/problems/closest-binary-search-tree-value/
class ClosestBinaryTreeValue {

  fun execute(input: TreeNode?, target: Double): Int {
    var root = input
    var closest = root?.`val` ?: Int.MAX_VALUE
    while (root != null) {
      val currentValue = root.`val`
      closest = if ((currentValue - target).absoluteValue < (closest - target).absoluteValue) currentValue else closest
      root = if (target < root.`val`) root.left else root.right
    }
    return closest
  }
}


fun main() {
  runTests(listOf(
      Triple(TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(5)), 3.714, 4),
      Triple(TreeNode(4, TreeNode(1, TreeNode(0), TreeNode(2, right = TreeNode(3))), TreeNode(5)), 3.1428, 3)
  )) { (input, target, value) -> value to ClosestBinaryTreeValue().execute(input, target) }
}