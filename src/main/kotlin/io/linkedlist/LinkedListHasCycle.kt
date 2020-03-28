package io.linkedlist

// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1212/
class LinkedListHasCycle {

  fun execute(head: ListNode?): Boolean {
    var fast: ListNode? = head?.next
    var slow: ListNode? = head
    while (fast != null){
      fast = fast.next?.next
      slow = slow?.next
      if (fast == slow)
        return true
    }
    return false
  }

  data class ListNode(var `val`: Int, var next: ListNode? = null)
}

