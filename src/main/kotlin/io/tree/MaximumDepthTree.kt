package io.tree

import kotlin.math.max

//https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/535/
class MaximumDepthTree {

  fun <T> execute(root: BinaryTree<T>?): Int = when (root) {
    null -> 0
    else -> max(execute(root.left), execute(root.right)) + 1
  }
}

fun main() {
  MaximumDepthTree().let { maximumDepthTree ->
    binaryTreeLetters().map {
      println("maximumDepthTree ${maximumDepthTree.execute(it)}")
    }

  }
}