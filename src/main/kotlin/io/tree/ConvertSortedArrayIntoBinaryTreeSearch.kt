package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
class ConvertSortedArrayIntoBinaryTreeSearch {

  fun execute(
      input: IntArray,
      leftBoundary: Int = 0,
      rightBoundary: Int = input.lastIndex): TreeNode? = when {
    input.isEmpty() -> null
    leftBoundary == rightBoundary -> TreeNode(input[leftBoundary])
    leftBoundary + 1 == rightBoundary -> TreeNode(input[leftBoundary]).apply { this.right = TreeNode(input[rightBoundary]) }
    else -> (leftBoundary + (rightBoundary - leftBoundary) / 2).let { index ->
      TreeNode(input[index]).apply {
        left = execute(input, leftBoundary, index - 1)
        right = execute(input, index + 1, rightBoundary)
      }
    }
  }
}

fun main() {
  runTests(listOf(
      intArrayOf(-10, -3, 0, 5, 9) to TreeNode(0, TreeNode(-10, right = TreeNode(-3)), TreeNode(5, right = TreeNode(9))),
      intArrayOf(0, 1) to TreeNode(0, right = TreeNode(1)),
      intArrayOf(0, 1, 2) to TreeNode(1, TreeNode(0), TreeNode(2)),
      intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8) to
          TreeNode(4,
              left = TreeNode(1, TreeNode(0), TreeNode(2, right = TreeNode(3))),
              right = TreeNode(6, TreeNode(5), TreeNode(7, right = TreeNode(8)))),
      intArrayOf(0, 1, 2, 3, 4, 5, 6, 7) to
          TreeNode(3,
              left = TreeNode(1, TreeNode(0), TreeNode(2)),
              right = TreeNode(5, TreeNode(4), TreeNode(6, right = TreeNode(7))))
  )) { (input, value) -> value to ConvertSortedArrayIntoBinaryTreeSearch().execute(input) }
}