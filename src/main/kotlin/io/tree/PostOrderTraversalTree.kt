package io.tree

import java.util.*

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/
class PostOrderTraversalTree : TraversalTree {
  override fun <T> executeIterative(tree: BinaryTree<T>): List<T> {
    val firstStack = Stack<BinaryTree<T>>()
    val secondStack = Stack<T>()
    firstStack.push(tree)
    while (firstStack.isNotEmpty()) {
      val current = firstStack.pop()
      secondStack.push(current.value)
      current.left?.let { firstStack.push(it) }
      current.right?.let { firstStack.push(it) }
    }

    return secondStack.toList().reversed()
  }

  override fun <T> executeRecursive(tree: BinaryTree<T>?, values: MutableList<T>): MutableList<T> = when (tree) {
    null -> values
    else -> executeRecursive(tree.right, executeRecursive(tree.left, values)).also { it.add(tree.value) }
  }
}

fun main() {
  runTraversalTree(PostOrderTraversalTree())
}