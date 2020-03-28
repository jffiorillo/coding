package io.linkedlist

//https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/
class MyLinkedList {

  data class Node(val value: Int, var next: Node? = null)

  private var size = 0
  private var root: Node? = null


  /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
  fun get(index: Int): Int = when (index) {
    in 0 until size -> (0 until index).fold(root!!) { acc, _ -> acc.next!! }.value
    else -> -1
  }

  /** Add a node of value val before the first element of the linked list.
   *  After the insertion, the new node will be the first node of the linked list. */
  fun addAtHead(newValue: Int) {
    root = Node(newValue, root)
    size++
  }

  /** Append a node of value val to the last element of the linked list. */
  fun addAtTail(newValue: Int) = when (root) {
    null -> root = Node(newValue)
    else -> {
      var current = root!!
      while (current.next != null) current = current.next!!
      current.next = Node(newValue)
    }
  }.also { size++ }

  /** Add a node of value val before the index-th node in the linked list.
   * If index equals to the length of linked list, the node will be appended to the end of linked list.
   * If index is greater than the length, the node will not be inserted. */
  fun addAtIndex(index: Int, newValue: Int): Unit = when (index) {
    0 -> root = Node(newValue, root).also { size++ }
    in 1..size -> {
      var current = root!!
      repeat(index - 1) { current = current.next!! }
      size++
      current.next = Node(newValue, current.next)
    }
    else -> {
    }
  }

  /** Delete the index-th node in the linked list, if the index is valid. */
  fun deleteAtIndex(index: Int) = when (index) {
    0 -> {
      @Suppress("ControlFlowWithEmptyBody")
      if (size > 0) {
        size--
        root = root!!.next
      } else {
      }
    }
    in 1 until size -> {
      size--
      var current = root!!
      repeat(index - 1) { current = current.next!! }
      current.next = current.next?.next
    }
    else -> {
    }
  }

}

fun main() {
  val myLinkedList = MyLinkedList()
  myLinkedList.addAtIndex(0,10)
  myLinkedList.addAtIndex(0,20)
  myLinkedList.addAtIndex(1,30)
  println(myLinkedList.get(0))

}