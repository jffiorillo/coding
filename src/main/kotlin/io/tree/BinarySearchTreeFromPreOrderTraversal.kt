package io.tree

import io.models.TreeNode
import io.utils.runTests

class BinarySearchTreeFromPreOrderTraversal {

  fun execute(A: IntArray, bound: Int = Int.MAX_VALUE, index: Int = 0): Pair<Int, TreeNode?> {
    if (index == A.size || A[index] > bound) return index to null
    val root = TreeNode(A[index])
    val (newIndex, treeNode) = execute(A, root.`val`, index + 1)
    root.left = treeNode
    val (rightIndex, right) = execute(A, bound, newIndex)
    root.right = right
    return rightIndex to root
  }

  fun executeAnotherApproach(input: IntArray): TreeNode? =
      if (input.isEmpty()) null else TreeNode(input.first()).also { create(input, it, it, 1, false) }

  private fun create(input: IntArray, node: TreeNode, father: TreeNode, index: Int, isRightRoot: Boolean): Int = when {
    index == input.size -> index
    input[index] < node.`val` -> {
      val rightIndex = create(input, TreeNode(input[index]).also { node.left = it }, node, index + 1, isRightRoot)
      create(input, node, father, rightIndex, isRightRoot)
    }
    node == father -> create(input, TreeNode(input[index]).also { node.right = it }, father, index + 1, isRightRoot || node == father)
    node.`val` < father.`val` && input[index] > father.`val` -> index
    else -> create(input, TreeNode(input[index]).also { node.right = it }, father, index + 1, isRightRoot || node == father)
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(8, 5, 1, 7, 10, 12) to
          TreeNode(8,
              left = TreeNode(5, TreeNode(1), TreeNode(7)),
              right = TreeNode(10, right = TreeNode(12))),
      intArrayOf(8) to TreeNode(8),
      intArrayOf(8, 15, 10, 17) to
          TreeNode(8,
              right = TreeNode(15,
                  left = TreeNode(10), right = TreeNode(17))),
      intArrayOf(15, 13, 12, 18) to
          TreeNode(15,
              left = TreeNode(13, left = TreeNode(12)),
              right = TreeNode(18))


  )) { (input, value) -> value to BinarySearchTreeFromPreOrderTraversal().execute(input).second }
}