package io.tree

import java.util.*

//https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/536/
class SymmetricTree {

  fun <T> execute(tree: BinaryTree<T>): Boolean {
    val stack = Stack<List<BinaryTree<T>?>>()
    stack.push(listOf(tree))
    while (stack.isNotEmpty()) {
      val current = stack.pop()
      val values = current.map { it?.value }
      if (current.filterNotNull().isEmpty()) {
        break
      } else if (values != values.reversed()) {
        return false
      }
      stack.push(current.flatMap { listOf(it?.left, it?.right) })
    }
    return true
  }
}

fun main() {
  SymmetricTree().let { symmetricTree ->
    symmetricTree().map {
      println("${symmetricTree.execute(it)}")
    }
  }
}