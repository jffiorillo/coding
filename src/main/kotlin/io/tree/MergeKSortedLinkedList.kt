package io.tree

import io.models.ListNode
import kotlin.math.min

// https://leetcode.com/problems/merge-k-sorted-lists/
class MergeKSortedLinkedList {

  fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null
    val result = ListNode(0)
    var current = result
    val values = lists.mapNotNull { it }.toMutableList()
    while (values.isNotEmpty()) {
      val (index, minTree) = values.foldIndexed(null as Pair<Int, ListNode>?) { index, acc, value ->
        when {
          acc == null || acc.second.`val` > value.`val` -> index to value
          else -> acc
        }
      }!!
      minTree.next?.let { values[index] = it } ?: values.removeAt(index)
      minTree.next = null
      current.next = minTree
      current = minTree
    }
    return result.next
  }
}