package io.linkedlist

import io.models.ListNode
import io.utils.runTests

// https://leetcode.com/problems/reorder-list/
class ReorderList {

  fun execute(root: ListNode?) {
    if (root == null) return
    var middle = root
    var fast = root
    while (fast?.next != null) {
      middle = middle!!.next
      fast = fast.next?.next
    }

    var secondPart = reverse(middle)

    var current: ListNode = root
    while (secondPart?.next != null) {
      val nextCurrent = current.next
      current.next = secondPart
      current = nextCurrent!!

      val nextSecondPart = secondPart.next
      secondPart.next = current
      secondPart = nextSecondPart
    }
  }

  fun reverse(root: ListNode?): ListNode? {
    var current = root
    var last: ListNode? = null
    while (current != null) {
      val nextCurrent = current.next
      current.next = last
      last = current
      current = nextCurrent
    }
    return last
  }

  // Time O(N2) Space O(1)
  fun executeN2(root: ListNode?) {
    var current = root
    while (current != null) {
      val nextCurrent = current.next
      val penultimate = getPenultimate(current)
      if (penultimate == current) break
      val last = penultimate.next
      penultimate.next = null
      last?.next = nextCurrent
      current.next = last
      current = nextCurrent
    }
  }

  fun getPenultimate(input: ListNode): ListNode {
    var current = input
    while (current.next?.next != null) current = current.next!!
    return current
  }
}

fun main() {
  runTests(listOf(
      ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))) to ListNode(1, ListNode(5, ListNode(2, ListNode(4, ListNode(3))))),
      ListNode(1, ListNode(2, ListNode(3, ListNode(4)))) to ListNode(1, ListNode(4, ListNode(2, ListNode(3))))
  )) { (input, value) -> value to input.apply { ReorderList().execute(this) } }
}