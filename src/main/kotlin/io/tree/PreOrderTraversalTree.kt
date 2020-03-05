@file:Suppress("MemberVisibilityCanBePrivate")

package io.tree

import java.util.*

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/
class PreOrderTraversalTree :TraversalTree {

  override fun <T> executeRecursive(tree: BinaryTree<T>?, values: MutableList<T>): MutableList<T> = when (tree) {
    null -> values
    else -> executeRecursive(tree.right, executeRecursive(tree.left, values.also { it.add(tree.value) }))
  }

  override fun <T> executeIterative(tree: BinaryTree<T>): List<T> {
    val result = mutableListOf<T>()
    val stack = Stack<BinaryTree<T>?>()
    stack.add(tree)
    while (stack.isNotEmpty()) {
      val item = stack.pop()
      item?.let { result.add(it.value) }
      item?.right?.let { stack.push(it) }
      item?.left?.let { stack.push(it) }
    }
    return result
  }
}

fun main() {
  runTraversalTree(PreOrderTraversalTree())
}