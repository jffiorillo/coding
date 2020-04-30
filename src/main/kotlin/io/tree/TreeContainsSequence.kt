package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/532/week-5/3315/
class TreeContainsSequence {

  fun execute(root: TreeNode?, sequence: IntArray, index: Int = 0): Boolean = when {
    index > sequence.lastIndex -> false
    index == sequence.lastIndex -> root?.`val` == sequence[index] && root.left == null && root.right == null
    root == null || root.`val` != sequence[index] -> false
    else -> execute(root.left, sequence, index + 1) || execute(root.right, sequence, index + 1)

  }
}

fun main() {
  runTests(listOf(
      Triple(TreeNode(0,
          left = TreeNode(1,
              left = TreeNode(0,
                  left = TreeNode(1)),
              right = TreeNode(1,
                  left = TreeNode(0),
                  right = TreeNode(0))
          ),
          right = TreeNode(0, TreeNode(0, TreeNode(0)))
      ), intArrayOf(0, 1, 1, 0), true),
      Triple(TreeNode(0,
          left = TreeNode(1,
              left = TreeNode(0,
                  left = TreeNode(1)),
              right = TreeNode(1,
                  left = TreeNode(0),
                  right = TreeNode(0))
          ),
          right = TreeNode(0, TreeNode(0, TreeNode(0)))
      ), intArrayOf(0, 1, 1), false),
      Triple(TreeNode(0,
          left = TreeNode(1,
              left = TreeNode(0,
                  left = TreeNode(1)),
              right = TreeNode(1,
                  left = TreeNode(0),
                  right = TreeNode(0))
          ),
          right = TreeNode(0, TreeNode(0), TreeNode(0))
      ), intArrayOf(0, 0, 0), true),
      Triple(TreeNode(0,
          left = TreeNode(1,
              left = TreeNode(0,
                  left = TreeNode(1)),
              right = TreeNode(1,
                  left = TreeNode(0),
                  right = TreeNode(0))
          ),
          right = TreeNode(0, TreeNode(0, TreeNode(0)))
      ), intArrayOf(0, 1, 0, 1), true)
  )) { (root, sequence, value) -> value to TreeContainsSequence().execute(root, sequence) }
}