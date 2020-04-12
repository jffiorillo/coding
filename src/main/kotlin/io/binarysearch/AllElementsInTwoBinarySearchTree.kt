package io.binarysearch

import io.models.TreeNode


// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
class AllElementsInTwoBinarySearchTree {

  fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> = execute(root1, root2)

  fun execute(root0: TreeNode?, root1: TreeNode?): List<Int> = when {
    root0 == null -> inOrderTraversal(root1)
    root1 == null -> inOrderTraversal(root0)
    else -> mutableListOf<Int>().apply {
      val elements0 = inOrderTraversal(root0)
      val elements1 = inOrderTraversal(root1)
      var index0 = 0
      var index1 = 0

      while (index0 < elements0.size && index1 < elements1.size) {
        if (elements0[index0] < elements1[index1]) {
          add(elements0[index0])
          index0++
        } else {
          add(elements1[index1])
          index1++
        }
      }
      (index0 until elements0.size).forEach { add(elements0[it]) }
      (index1 until elements1.size).forEach { add(elements1[it]) }
    }
  }

  private fun inOrderTraversal(root: TreeNode?, acc: List<Int> = emptyList()): List<Int> = when (root) {
    null -> acc
    else -> inOrderTraversal(root.right, inOrderTraversal(root.left, acc) + root.`val`)
  }
}

fun main() {
  val allElementsInTwoBinarySearchTree = AllElementsInTwoBinarySearchTree()
  listOf(
      Triple(TreeNode(2, TreeNode(1), TreeNode(4)), TreeNode(1, TreeNode(0), TreeNode(3)), listOf(0, 1, 1, 2, 3, 4))
  ).forEachIndexed { index, (root0, root1, value) ->
    val output = allElementsInTwoBinarySearchTree.execute(root0, root1)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}

