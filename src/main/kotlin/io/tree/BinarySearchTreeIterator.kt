package io.tree

import io.models.TreeNode
import java.util.*

// https://leetcode.com/problems/binary-search-tree-iterator/
class BinarySearchTreeIterator(root: TreeNode?) {

  private val information: List<Int>
  private var index = 0

  init {
    val list = mutableListOf<Int>()
    inOrderTraversal(root, list)
    information = list
  }

  private fun inOrderTraversal(node: TreeNode?, accum: MutableList<Int>) {
    if (node == null) return
    inOrderTraversal(node.left, accum)
    accum.add(node.`val`)
    inOrderTraversal(node.right, accum)
  }

  /** @return the next smallest number */
  fun next(): Int = information[index++]

  /** @return whether we have a next smallest number */
  fun hasNext(): Boolean = index in information.indices
}

// next: Space O(h) where h is the height of the Tree
// next: Time complexity avg O(1) explanation here https://leetcode.com/problems/binary-search-tree-iterator/solution/
class BinarySearchTreeIterator2(var root: TreeNode?) {

  private val stack = LinkedList<Pair<TreeNode, Boolean>>().apply { root?.let { push(it to false) } }

  private fun inOrderTraversal(node: TreeNode?, visitedLeft: Boolean): Int? {
    if (node == null) return null
    if (node.left != null && !visitedLeft) {
      stack.push(node to true)
      return inOrderTraversal(node.left, false)
    }
    node.right?.let { stack.push(it to false) }
    return node.`val`
  }

  /** @return the next smallest number */
  fun next(): Int = stack.pop().let { (node, visitedLeft) -> inOrderTraversal(node, visitedLeft)!! }

  /** @return whether we have a next smallest number */
  fun hasNext(): Boolean = stack.isNotEmpty()
}