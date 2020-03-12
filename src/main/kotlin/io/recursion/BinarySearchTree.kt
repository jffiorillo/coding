package io.recursion

import io.TreeNode

//https://leetcode.com/explore/featured/card/recursion-i/251/scenario-i-recurrence-relation/3233/
class BinarySearchTree {

  fun executeRecursive(root: TreeNode?, target: Int): TreeNode? = when {
    root == null -> root
    root.`val` == target -> root
    root.`val` < target -> executeRecursive(root.right, target)
    else -> executeRecursive(root.left, target)
  }

  fun executeIterative(root: TreeNode?, target: Int): TreeNode? {
    var current = root
    loop@ while (current != null) {
      when {
        current.`val` == target -> break@loop
        current.`val` < target -> current = current.right
        else -> current = current.left
      }
    }
    return current
  }
}