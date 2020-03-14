package io.recursion

import io.TreeNode
import java.util.*

class SameBinaryTree {


  fun execute(p: TreeNode?, q: TreeNode?): Boolean {
    val stack = LinkedList<Pair<TreeNode, TreeNode>>()

    if (p == null || q == null) return q == p

    stack.push(p to q)

    while (stack.isNotEmpty()) {
      stack.pop().let { pair ->
        when {
          pair.first.`val` != pair.second.`val` -> return false

          (pair.first.left == null || pair.second.left == null) && pair.first.left != pair.second.left -> return false

          (pair.first.right == null || pair.second.right == null) && pair.first.right != pair.second.right -> return false

          else -> {
            if (pair.first.left != null) stack.push(pair.first.left!! to pair.second.left!!)
            if (pair.first.right != null) stack.push(pair.first.right!! to pair.second.right!!)
          }
        }
      }
    }
    return true
  }
}