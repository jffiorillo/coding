package io.linkedlist

// https://leetcode.com/problems/copy-list-with-random-pointer/
private class CopyListWithRandomPointer {

  // Time O(N) Space O(N)
  fun executeWithExtraSpace(node: Node?): Node? {
    var current = node
    val root = Node()
    var currentCopy = root
    val map = mutableMapOf<Node, Node>()
    while (current != null) {
      val nextCurrentCopy = Node(current.`val`)
      map[current] = nextCurrentCopy
      currentCopy.next = nextCurrentCopy
      currentCopy = nextCurrentCopy
      current = current.next
    }
    current = node
    while (current != null) {
      current.random?.let { map.getValue(current!!).random = map.getValue(it) }
      current = current.next
    }
    return root.next
  }
}

private data class Node(val `val`: Int? = null, var next: Node? = null, var random: Node? = null)