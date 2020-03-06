@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.tree

import kotlin.math.absoluteValue

//https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/537/
class PathSum {

  fun execute(tree: BinaryTree<Int>, value: Int): Boolean = executeRecursive(tree, tree.left == null && tree.right == null, 0, value)

  fun executeRecursive(tree: BinaryTree<Int>?, isLeaf: Boolean, accumulate: Int, value: Int): Boolean = when {
    tree == null -> accumulate == value && isLeaf
    else -> executeRecursive(tree.left, tree.left == null && tree.right == null, accumulate + tree.value, value)
        || executeRecursive(tree.right, tree.left == null && tree.right == null, accumulate + tree.value, value)
  }
}


fun main() {
  listOf(
      Pair(BinaryTree(5,
          left = BinaryTree(4,
              left = BinaryTree(11,
                  left = BinaryTree(7),
                  right = BinaryTree(2))),
          right = BinaryTree(8,
              left = BinaryTree(13),
              right = BinaryTree(4,
                  right = BinaryTree(1)))
      ), 22),
      Pair(BinaryTree(1, right = BinaryTree(2)), 1),
      Pair(BinaryTree(-2, right = BinaryTree(-3)), -5),
      Pair(BinaryTree(8,
          left = BinaryTree(9),
          right = BinaryTree(-6,
              left = BinaryTree(5),
              right = BinaryTree(9))), 7)
  ).map { (tree, value) ->
    println("${PathSum().execute(tree, value)}")
  }
}