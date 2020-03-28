package io.linkedlist

import io.models.ListNode

// https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/
class RemoveAllElementsWhere {

  fun execute(head: ListNode?, value: Int): ListNode? {
    var current = nextDifferent(head, value)
    val newHead = current
    while (current?.next != null) {
      if (current.next?.`val` == value) {
        current.next = nextDifferent(current.next, value)
      }
      current = current.next
    }
    return newHead
  }

  private fun nextDifferent(head: ListNode?, value: Int): ListNode? {
    var current: ListNode? = head
    while (current?.`val` == value) {
      current = current.next
    }
    return current
  }

}