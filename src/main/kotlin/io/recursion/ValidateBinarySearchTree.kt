package io.recursion

import io.TreeNode
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/explore/learn/card/recursion-ii/470/divide-and-conquer/2874/
class ValidateBinarySearchTree {

  fun executeRecursive(node: TreeNode?, lower: Int?, upper: Int?): Boolean = when {
    node == null -> true
    lower != null && node.`val` <= lower -> false
    upper != null && node.`val` >= upper -> false
    else -> executeRecursive(node.left, lower, node.`val`) && executeRecursive(node.right, node.`val`, upper)
  }
}