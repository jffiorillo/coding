package io.tree

import io.models.TreeNode

// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/932/
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
class LowestCommonAncestor {

  fun <T> execute(root: BinaryTree<T>?, p: T, q: T): BinaryTree<T>? = when {
    root == null -> null
    p == root.value || q == root.value -> root
    else -> {
      val left = execute(root.left, p, q)
      val right = execute(root.right, p, q)
      if (left != null && right != null) {
        root
      } else left ?: right
    }
  }

  fun execute(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? = when {
    root == null -> null
    root == p || root == q -> root
    else -> {
      val left = execute(root.left, p, q)
      val right = execute(root.right, p, q)
      if (left != null && right != null) root else left ?: right
    }
  }
}


fun main() {
  val lowestCommonAncestor = LowestCommonAncestor()
  val tree = BinaryTree(3,
      left = BinaryTree(5,
          left = BinaryTree(6),
          right = BinaryTree(2, left = BinaryTree(7), right = BinaryTree(4))),
      right = BinaryTree(1, left = BinaryTree(0), right = BinaryTree(8))
  )
  listOf(
      IWrapper(tree, 5, 1, 3),
      IWrapper(tree, 5, 4, 5),
      IWrapper(tree, 6, 4, 5),
      IWrapper(tree, 7, 0, 3)

  ).map { (tree, p, q, result) ->
    val node = lowestCommonAncestor.execute(tree, p, q)
    println("${node?.value == result}")
  }
}

data class IWrapper<T>(val tree: BinaryTree<T>, val p: T, val q: T, val result: T)