package io.tree

interface TraversalTree {
  fun <T> executeIterative(tree: BinaryTree<T>): List<T>
  fun <T> executeRecursive(tree: BinaryTree<T>?, values: MutableList<T>): MutableList<T>
}