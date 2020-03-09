package io.recursion

import java.util.*
import java.util.spi.CurrencyNameProvider

// https://leetcode.com/explore/featured/card/recursion-i/251/scenario-i-recurrence-relation/2378/
class ReverseLinkedList {

  fun executeRecursive(head: ListNode?): ListNode? = when (head?.next) {
    null -> head
    else -> {
      executeRecursive(head.next)?.also {
        head.next!!.next = head
        head.next = null
      }
    }
  }

  fun executeIterative(head: ListNode?): ListNode? {
    if (head == null) return null
    var curr: ListNode? = head
    var prev: ListNode? = null
    while (curr != null) {
      val next = curr.next
      curr.next = prev
      prev = curr
      curr = next
    }
    return prev
  }
}

fun main() {
  val reverseLinkedList = ReverseLinkedList()
  listOf(ListNode(1).apply {
    this.next = ListNode(2).apply {
      this.next = ListNode(3).apply {
        this.next = ListNode(4)
      }
    }
  }).map { input ->
    val execute = reverseLinkedList.executeIterative(input)
    println("$execute")
  }
}