package io.linkedlist

import io.models.ListNode

// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3290/
class MiddleOfLinkedList {

  fun execute(head: ListNode?): ListNode? =
    head?.size()?.let { elem ->
      var current = head
      repeat(elem/2){current = current?.next}
      current
    }

  fun executeFastSlowPointer(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while(fast?.next != null){
      slow = slow?.next
      fast = fast.next?.next
    }
    return slow
  }


}