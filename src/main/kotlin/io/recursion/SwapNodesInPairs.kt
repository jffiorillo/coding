package io.recursion

import io.models.ListNode

//https://leetcode.com/explore/featured/card/recursion-i/250/principle-of-recursion/1681/
class SwapNodesInPairs {
  fun execute(node: ListNode? = null): ListNode? = when {
    node == null -> null
    node.next == null -> node
    else -> {
      node.next?.let { next ->
        node.next = execute(next.next)
        next.next = node
        next
      }
    }
  }
}
