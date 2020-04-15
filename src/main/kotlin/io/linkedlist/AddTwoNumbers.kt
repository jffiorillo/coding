package io.linkedlist

import io.models.ListNode
import io.utils.runTests

// https://leetcode.com/problems/add-two-numbers/
class AddTwoNumbers {

  fun execute(l0: ListNode?, l1: ListNode?): ListNode? {
    var current0 = l0
    var current1 = l1
    var result: ListNode? = null
    var lastResult = result
    var accum = 0
    while (current0 != null && current1 != null) {
      val sum = current0.`val` + current1.`val` + accum
      val value = sum.rem(10)
      accum = sum / 10
      if (result == null) {
        result = ListNode(value)
        lastResult = result
      } else {
        lastResult!!.next = ListNode(value)
        lastResult = lastResult.next
      }
      current0 = current0.next
      current1 = current1.next
    }
    update(result, current0, accum).let { (accum0, result0) ->
      accum = accum0
      result = result0

    }
    update(result, current1, accum).let { (accum0, result0) ->
      accum = accum0
      result = result0
    }
//    if (accum != 0)
//      result = update(result, ListNode(accum), 0).second
    return result
  }

  fun update(result: ListNode?, rest: ListNode?, accum0: Int): Pair<Int, ListNode?> {
    var currentResult = result
    var lastResult = currentResult
    while (lastResult?.next != null) {
      lastResult = lastResult.next
    }
    var current = rest
    var accum = accum0
    while (current != null) {
      val sum = current.`val` + accum
      val value = sum.rem(10)
      accum = sum / 10
      if (currentResult == null) {
        currentResult = ListNode(value)
        lastResult = currentResult
      } else {
        lastResult!!.next = ListNode(value)
        lastResult = lastResult.next
      }
      current = current.next
    }
    return accum to currentResult
  }
}

fun main() {
  runTests(listOf(
      Triple(ListNode(2, ListNode(4, ListNode(3))), ListNode(5, ListNode(6, ListNode(4))), ListNode(7, ListNode(0, ListNode(8)))),
      Triple(ListNode(9, ListNode(8)), ListNode(1), ListNode(0, ListNode(9))),
      Triple(ListNode(9, ListNode(1, ListNode(6))), ListNode(0), ListNode(9, ListNode(1, ListNode(6))))
  )) { (i0, i1, value) -> value to AddTwoNumbers().execute(i0, i1) }
}