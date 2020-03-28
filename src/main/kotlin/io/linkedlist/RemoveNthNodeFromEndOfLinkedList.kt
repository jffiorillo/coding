package io.linkedlist

import io.models.ListNode

// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/
class RemoveNthNodeFromEndOfLinkedList {

  fun execute(head: ListNode?, n: Int): ListNode? = when (head?.size()) {
    null, 1 -> null
    n -> head.next
    else -> {
      val size = head.size()
      if (n < size) {
        var current: ListNode = head
        repeat(size - n-1) {
          current = current.next!!
        }
        current.next = current.next!!.next
      }
      head
    }
  }

}