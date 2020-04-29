package io.queue

import io.models.ListNode
import io.utils.runTests
import java.util.*


// https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
class RemoveOppositeConsecutiveNodes {


  // The observation here is that the sum from index 0 to index M will be
  // equal to sum from index 0 to index N if sum from index (M+1) to index N is 0.
  // Thus, here we track the sum from index 0 to each index, using a Map to indicate
  // the farthest index N that we can remove from index M, then we shall be able to
  // remove M+1 -> N and continue from N+1. This works since we don't have to optimize
  // for the number of sequences to be removed
  fun execute(head: ListNode?): ListNode? {

    // Map from sum from index 0 to the farthest value that the sum stays unchanged.
    val sumToFarthestNodeMap: MutableMap<Int, ListNode> = HashMap()

    // Need the dummy node to track the new head if changed.
    val preHead = ListNode(0)
    preHead.next = head

    // First iteration to compute the map.
    var sum = 0
    var current: ListNode? = preHead
    while (current != null) {
      sum += current.`val`
      sumToFarthestNodeMap[sum] = current
      current = current.next
    }

    // Second iteration to re-connect the nodes to the farthest node where the sum stays unchanged
    sum = 0
    current = preHead
    while (current != null) {
      sum += current.`val`
      current.next = sumToFarthestNodeMap[sum]!!.next
      current = current.next
    }

    // Done, return the head from preHead
    return preHead.next
  }
}

fun main() {
  runTests(listOf(
      ListNode(1, ListNode(2, ListNode(3, ListNode(-3, ListNode(-2, ListNode(-1, ListNode(3))))))) to ListNode(3),
      ListNode(1, ListNode(-1)) to null,
      null to null,
      ListNode(5, ListNode(-3, ListNode(-4, ListNode(1, ListNode(6, ListNode(-2, ListNode(-5))))))) to ListNode(5, ListNode(-2, ListNode(-5))),
      ListNode(0) to null
  )) { (input, value) -> value to RemoveOppositeConsecutiveNodes().execute(input) }
}