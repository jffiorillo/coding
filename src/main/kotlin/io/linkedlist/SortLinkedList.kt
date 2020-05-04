package io.linkedlist

import io.models.ListNode

// https://leetcode.com/problems/sort-list/
class SortLinkedList {

  fun execute(head: ListNode?): ListNode? {
    if (head == null) return null
    val size = head.size()
    if (size == 1) return head
    val secondPart = split(head, size)
    return doMerge(execute(head), execute(secondPart))
  }

  private fun split(head: ListNode?, size: Int): ListNode? {
    var current = head
    val amount = size / 2 - if (size.rem(2) == 0) 1 else 0
    repeat(amount) { current = current!!.next }
    val previous = current!!
    current = current!!.next
    previous.next = null
    return current

  }

  private fun doMerge(input0: ListNode?, input1: ListNode?): ListNode? {
    val result = ListNode(0)
    var current = result
    var first = input0
    var second = input1

    while (first != null && second != null) {
      if (first.`val` < second.`val`) {
        current.next = first
        first = first.next
      } else {
        current.next = second
        second = second.next
      }
      current.next!!.next = null
      current = current.next!!
    }
    if (first != null) current.next = first
    if (second != null) current.next = second
    return result.next
  }
}

fun main() {

}