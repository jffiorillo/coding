package io.linkedlist

// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1212/
// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1214/
class LinkedListHasCycle {

  data class ListNode(var `val`: Int, var next: ListNode? = null)

  fun execute(head: ListNode?): Boolean {
    var faster: ListNode? = head?.next
    var slower: ListNode? = head
    while (faster != null){
      faster = faster.next?.next
      slower = slower?.next
      if (faster == slower)
        return true
    }
    return false
  }


  fun getListNodeCycle(head:ListNode?): ListNode? {
    var faster: ListNode? = head
    var slower: ListNode? = head
    while (faster != null){
      faster = faster.next?.next
      slower = slower?.next
      if (faster != null && faster == slower){
        slower = head
        while(faster != slower){
          slower = slower!!.next
          faster = faster!!.next
        }
        return slower
      }
    }
    return null
  }
}

