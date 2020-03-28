package io.linkedlist

import io.models.ListNode
import kotlin.math.absoluteValue

// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1215/
class GetIntersectionInTwoLinkedList {

  fun execute(head0: ListNode?, head1: ListNode?): ListNode? = when {
    head0 == null || head1 == null -> null
    head0 == head1 -> head0
    else -> {
      val size0 = head0.size()
      val size1 = head1.size()
      val diff = (size0 - size1).absoluteValue
      var bigger: ListNode? = if (size0 < size1) head1 else head0
      var smaller: ListNode? = if (size0 < size1) head0 else head1

      repeat(diff) { bigger = bigger!!.next!! }
      while (bigger != null && smaller != null) {
        if (bigger == smaller) break
        bigger = bigger!!.next
        smaller = smaller.next
      }
      if (bigger != null && bigger == smaller) bigger else null
    }
  }

}

fun main() {
  val getIntersectionInTwoLinkedList = GetIntersectionInTwoLinkedList()
  val inter = ListNode(8, ListNode(4, ListNode(5)))
  val output = getIntersectionInTwoLinkedList.execute(ListNode(4, ListNode(3, inter)), ListNode(5, ListNode(0, ListNode(1, inter))))

  println(output)

}