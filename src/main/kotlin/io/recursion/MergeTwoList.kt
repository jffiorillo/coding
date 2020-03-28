package io.recursion

import io.models.ListNode

//https://leetcode.com/explore/featured/card/recursion-i/253/conclusion/2382/
class MergeTwoList {

  fun execute(list: ListNode?, list1: ListNode?): ListNode? = when {
    list == null -> list1
    list1 == null -> list
    list1.`val` > list.`val` ->
      ListNode(list.`val`).apply {
        next = execute(list.next, list1)
      }
    else ->
      ListNode(list1.`val`).apply {
        next = execute(list, list1.next)
      }
  }
}