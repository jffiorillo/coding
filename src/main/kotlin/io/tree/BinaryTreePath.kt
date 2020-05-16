@file:Suppress("unused")

package io.tree

import io.models.TreeNode


// https://leetcode.com/problems/binary-tree-paths/
class BinaryTreePath {


  fun execute(root: TreeNode?, path: String = "", accum: MutableList<String> = mutableListOf()): List<String> = when {
    root == null -> accum
    root.left == null && root.right == null -> accum.apply { add(path + "${root.`val`}") }
    else -> accum.apply {
      if (root.left != null) execute(root.left, "$path${root.`val`}->", accum)
      if (root.right != null) execute(root.right, "$path${root.`val`}->", accum)
    }
  }
}