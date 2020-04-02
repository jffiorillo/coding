package io.linkedlist

import io.models.ListNode

class NextLargerNode {

  fun execute(head: ListNode?): IntArray = IntArray(head?.size() ?: 0).apply {
    var current = head
    var index = 0
    while (current != null) {
      var next = current.next
      while (next != null && next.`val` <= current.`val`) next = next.next
      this[index] = next?.`val` ?: 0
      index++
      current = current.next
    }
  }
}