package io.tree

import io.models.TreeNode
import io.utils.runTests

// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
class SmallestSubtreeWithAllDeepestNodes {


  fun execute(root: TreeNode?): TreeNode? = when (root) {
    null -> null
    else -> {
      var stack = listOf(Route(root))

      while (stack.isNotEmpty()) {
        val nextStack = stack.flatMap { route -> route.children() }
        if (nextStack.isEmpty()) {
          break
        }
        stack = nextStack
      }
      val first = stack.first()
      when (stack.size) {
        1 -> first.last()
        else -> {
          var index = 0
          while (stack.all {
                index + 1 in first.nodes.indices &&
                    index + 1 in it.nodes.indices &&
                    first.nodes[index + 1].`val` == it.nodes[index + 1].`val`
              }) {
            index++
          }
          first.nodes[index]
        }
      }
    }
  }

  private data class Route(val nodes: MutableList<TreeNode> = mutableListOf()) {
    constructor(root: TreeNode) : this(mutableListOf(root))

    fun last() = nodes.last()

    fun add(node: TreeNode) = nodes.add(node)

    fun nextRight() = last().right?.let { copy(nodes = mutableListOf<TreeNode>().apply { addAll(nodes) }).apply { this.add(it) } }
    fun nextLeft() = last().left?.let { copy(nodes = mutableListOf<TreeNode>().apply { addAll(nodes) }).apply { this.add(it) } }

    fun children(): List<Route> {
      val nextRight = nextRight()
      val nextLeft = nextLeft()
      return when {
        nextLeft == null && nextRight == null -> emptyList()
        nextLeft == null -> listOf(nextRight!!)
        nextRight == null -> listOf(nextLeft)
        else -> listOf(nextLeft, nextRight)
      }
    }
  }

  // https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/discuss/146808/C%2B%2BJavaPython-One-Pass
  fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? = deep(root).second
  private fun deep(root: TreeNode?): Pair<Int, TreeNode?> {
    if (root == null) return Pair(0, null)
    val left = deep(root.left)
    val right = deep(root.right)
    return Pair(maxOf(left.first, right.first) + 1,
        when {
          left.first == right.first -> root
          right.first > left.first -> left.second
          else -> right.second
        })
  }
}

fun main() {
  runTests(listOf(
      TreeNode(3,
          left = TreeNode(5,
              left = TreeNode(6),
              right = TreeNode(2, TreeNode(7), TreeNode(4))),
          right = TreeNode(1, TreeNode(0), TreeNode(8)))
          to TreeNode(2, TreeNode(7), TreeNode(4))
  )) { (input, value) -> value to SmallestSubtreeWithAllDeepestNodes().execute(input) }
}
