package io.recursion

import io.TreeNode

//https://leetcode.com/explore/featured/card/recursion-i/253/conclusion/2384/

class UniqueBinarySearchTreeGenerator {

  fun execute(height: Int): List<TreeNode?> = executeRecursive((1..height).toList())

  fun executeRecursive(number: List<Int>): List<TreeNode> = when {
    number.isEmpty() -> emptyList()
    number.size == 1 -> listOf(TreeNode(number.first()))
    else -> {
      number.flatMap { currentNumber ->
        val executeRecursive = executeRecursive(number - currentNumber)
        if (executeRecursive.isNotEmpty()) {
          executeRecursive.map { tree ->
            tree.addNewToTree(TreeNode(currentNumber))
          }
        } else {
          listOf(TreeNode(currentNumber))
        }
      }
    }
  }


  private fun TreeNode.addNewToTree(newNode: TreeNode): TreeNode = apply {
    var current: TreeNode = this
    while (true) {
      if (current.`val` < newNode.`val`) {
        if (current.right == null) {
          current.right = newNode
          break
        }
        current = current.right!!
      } else {
        if (current.left == null) {
          current.left = newNode
          break
        }
        current = current.left!!
      }
    }
  }

  fun areEquals(another: TreeNode?, node: TreeNode?): Boolean = when {
    another == null -> node == null
    node == null -> false
    else -> another.`val` == node.`val` && areEquals(another.left, node.left) && areEquals(another.right, node.right)
  }

}


fun main() {
  val generator = UniqueBinarySearchTreeGenerator()
  generator.execute(3).map {
    println(it)
  }
}