package io.models

data class ListNode(var `val`: Int, var next: ListNode? = null) {

  fun size(): Int {
    var current: ListNode? = this
    var size = 0
    while (current != null) {
      current = current.next
      size++
    }
    return size
  }
}