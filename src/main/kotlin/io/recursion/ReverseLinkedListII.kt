package io.recursion

import io.models.ListNode
import io.utils.runTests


// https://leetcode.com/problems/reverse-linked-list-ii/
class ReverseLinkedListII {

  fun execute(head: ListNode?, first: Int, last: Int): ListNode? {
    if (head == null || first == last)
      return head
    var root = head
    var r: ListNode? = head
    var f = head
    repeat(first - 2) {
      r = r!!.next!!
    }
    if (first != 1)
      f = r!!.next!!
    val current = f
    var temp = current.next
    repeat(last - first) {
      current.next = temp!!.next
      if (first == 1) {
        r = temp
        root = r
      } else r!!.next = temp
      temp!!.next = f
      f = temp!!
      temp = current.next
    }
    return root
  }
}

fun main() {
  runTests(listOf(
      listOf(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 2, 4, ListNode(1, ListNode(4, ListNode(3, ListNode(2, ListNode(5)))))),
      listOf(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 1, 5, ListNode(5, ListNode(4, ListNode(3, ListNode(2, ListNode(1)))))),
      listOf(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 3, 3, ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))),
      listOf(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 3, 4, ListNode(1, ListNode(2, ListNode(4, ListNode(3, ListNode(5)))))),
      listOf(ListNode(1), 1, 1, ListNode(1)),
      listOf(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 4, 5, ListNode(1, ListNode(2, ListNode(3, ListNode(5, ListNode(4))))))
  )) { (input, first, last, value) -> value to ReverseLinkedListII().execute(input as ListNode?, first as Int, last as Int) }
}