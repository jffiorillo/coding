package io.tree

import io.models.TreeNode
import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/increasing-order-search-tree/
class RearrangeTree {

  fun executeRecursive(input: TreeNode?, acc: TreeNode? = null): TreeNode? = when (input) {
    null -> acc
    else -> {
      executeRecursive(input.right, executeRecursive(input.left, acc)?.also {
        var current = it
        while (current.right != null) current = current.right!!
        current.right = TreeNode(input.`val`)
      } ?: TreeNode(input.`val`))
    }
  }

  fun executeIterative(input: TreeNode?): TreeNode? {
    var root: TreeNode? = null
    var current = input
    var last: TreeNode? = null
    val stack = LinkedList<TreeNode>()
    while (current != null || stack.isNotEmpty()) {
      while (current != null) {
        stack.push(current)
        current = current.left
      }
      if (stack.isNotEmpty()) {
        val item = stack.pop()
        TreeNode(item.`val`).let { newNode ->
          if (last == null) {
            root = newNode
            last = newNode
          } else {
            last!!.right = newNode
            last = newNode
          }
        }
        current = item?.right
      }
    }
    return root
  }
}

fun main() {
  runTests(
      listOf(
          Pair(TreeNode(5,
              left = TreeNode(3,
                  left = TreeNode(2, left = TreeNode(1)),
                  right = TreeNode(4)),
              right = TreeNode(6,
                  right = TreeNode(8, left = TreeNode(7), right = TreeNode(9)))),
              TreeNode(1,
                  right = TreeNode(2,
                      right = TreeNode(3,
                          right = TreeNode(4,
                              right = TreeNode(5,
                                  right = TreeNode(6,
                                      right = TreeNode(7,
                                          right = TreeNode(8,
                                              right = TreeNode(9)))))))))
          )
      )
  ) { (input, value) -> Pair(value, RearrangeTree().executeRecursive(input)) }
}