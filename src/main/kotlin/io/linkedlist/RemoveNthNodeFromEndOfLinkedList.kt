@file:Suppress("unused")

package io.linkedlist

import io.models.ListNode

// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/
class RemoveNthNodeFromEndOfLinkedList {

  fun execute(head: ListNode?, n: Int): ListNode? = when (head) {
    null -> null
    else -> {
      var faster: ListNode = head
      var slower: ListNode = head
      repeat(n) { faster = faster.next ?: return head.next }
      while (faster.next != null) {
        faster = faster.next!!
        slower = slower.next!!
      }
      slower.next = slower.next!!.next
      head
    }
  }

}