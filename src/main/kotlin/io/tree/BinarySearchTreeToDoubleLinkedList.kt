@file:Suppress("unused")

package io.tree

import io.models.Node

// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
class BinarySearchTreeToDoubleLinkedList {

  fun execute(root: Node?) = executeRecursion(root)?.let { (first, last) ->
    first.left = last
    last.right = first
    first
  }

  private fun executeRecursion(root: Node?): Pair<Node, Node>? = when (root) {
    null -> null
    else -> {
      var first: Node = root
      executeRecursion(root.left)?.let { (lFirst, lLast) ->
        lLast.right = root
        root.left = lLast
        first = lFirst
      }
      var last: Node = root
      executeRecursion(root.right)?.let { (rFirst, rLast) ->
        rFirst.left = root
        root.right = rFirst
        last = rLast
      }
      first to last
    }
  }
}