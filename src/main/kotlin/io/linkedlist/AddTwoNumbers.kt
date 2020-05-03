package io.linkedlist

import io.models.ListNode
import io.utils.runTests


// https://leetcode.com/problems/add-two-numbers/
@Suppress("DuplicatedCode")
class AddTwoNumbers {

  fun execute1(l0: ListNode?, l1: ListNode?): ListNode? {
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
    return result
  }

  private fun update(result: ListNode?, rest: ListNode?, accum0: Int): Pair<Int, ListNode?> {
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

  fun execute(l1: ListNode?, l2: ListNode?): ListNode? = ListNode(0).let { sentinel ->
    var c1 = l1
    var c2 = l2
    var lastSumNode = sentinel
    var sum = 0
    while (c1 != null || c2 != null) {
      sum /= 10
      c1?.also {
        sum += it.`val`
        c1 = it.next
      }
      c2?.also {
        sum += it.`val`
        c2 = it.next
      }
      lastSumNode.next = ListNode(sum % 10).also { lastSumNode = it }
    }
    if (sum / 10 == 1) lastSumNode.next = ListNode(1)
    sentinel.next
  }
}

fun main() {
  runTests(listOf(
      Triple(ListNode(2, ListNode(4, ListNode(3))), ListNode(5, ListNode(6, ListNode(4))), ListNode(7, ListNode(0, ListNode(8)))),
      Triple(ListNode(9, ListNode(8)), ListNode(1), ListNode(0, ListNode(9))),
      Triple(ListNode(9, ListNode(1, ListNode(6))), ListNode(0), ListNode(9, ListNode(1, ListNode(6))))
  )) { (i0, i1, value) -> value to AddTwoNumbers().execute(i0, i1) }
}