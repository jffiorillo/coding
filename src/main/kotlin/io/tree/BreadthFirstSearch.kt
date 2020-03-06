package io.tree

import java.util.*

class BreadthFirstSearch : TraversalTree {
  override fun <T> executeIterative(tree: BinaryTree<T>): List<T> {
    val stack = Stack<List<BinaryTree<T>>>()
    val result = mutableListOf<List<T>>()
    stack.push(listOf(tree))
    while (stack.isNotEmpty()) {
      val current = stack.pop()
      result.add(current.map { it.value })
      current.flatMap { listOfNotNull(it.left, it.right) }.let { news ->
        if (news.isNotEmpty()) stack.push(news)
      }
    }
    println("BreadthFirstSearch.executeIterative  $result")
    return result.flatten()
  }

  override fun <T> executeRecursive(tree: BinaryTree<T>?, values: MutableList<T>): MutableList<T> =
      tree?.let { executeRecursive(listOf(it), mutableListOf()).flatten().toMutableList() } ?: mutableListOf()

  private fun <T> executeRecursive(nodes: List<BinaryTree<T>>, values: MutableList<List<T>>): MutableList<List<T>> = when {
    nodes.isEmpty() -> values
    else -> executeRecursive(nodes.flatMap { listOfNotNull(it.left, it.right) }, values.also { list -> list.add(nodes.map { it.value }) })
  }
}

fun main() {
  runTraversalTree(BreadthFirstSearch())
}