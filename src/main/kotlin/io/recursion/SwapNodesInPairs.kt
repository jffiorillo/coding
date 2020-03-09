package io.recursion

//https://leetcode.com/explore/featured/card/recursion-i/250/principle-of-recursion/1681/
class SwapNodesInPairs {
  fun execute(node: ListNode?= null): ListNode? =when{
    node == null -> null
    node.next == null -> node
    node.next?.next == null -> {
      node.next?.let{ child->
        child.next = node
        node.next = null
        child
      }
    }
    else -> {
      node.next?.next.let{ childChild->
        node.next?.let { child ->
          child.next = node
          node.next = execute(childChild)
          child
        }
      }
    }
  }

  data class ListNode(val `val`: Int, var next: ListNode? = null)
}