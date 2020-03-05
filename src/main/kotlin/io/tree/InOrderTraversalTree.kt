@file:Suppress("MemberVisibilityCanBePrivate")

package io.tree

import java.util.*

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/
class InOrderTraversalTree : TraversalTree {

  override fun <T> executeRecursive(tree: BinaryTree<T>?, values: MutableList<T>): MutableList<T> = when (tree) {
    null -> values
    else -> executeRecursive(tree.right, executeRecursive(tree.left, values).also { it.add(tree.value) })
  }

  override fun <T> executeIterative(tree: BinaryTree<T>): List<T> {
    val result = mutableListOf<T>()
    val stack = Stack<BinaryTree<T>>()
    var current: BinaryTree<T>? = tree
    while (current != null || stack.isNotEmpty()) {
      while (current != null) {
        stack.push(current)
        current = current.left
      }
      if (stack.isNotEmpty()) {
        val item = stack.pop()
        result.add(item.value)
        current = item.right
      }
    }

    return result
  }
}

fun main() {
  runTraversalTree(InOrderTraversalTree())
}