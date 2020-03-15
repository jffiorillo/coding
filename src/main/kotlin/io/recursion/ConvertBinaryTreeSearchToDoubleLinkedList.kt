@file:Suppress("MemberVisibilityCanBePrivate")

package io.recursion

import io.tree.BinaryTree


class ConvertBinaryTreeSearchToDoubleLinkedList {

  fun <T> execute(treeNode: BinaryTree<T>?): DoubleLinkedNode<T>? = when (treeNode) {
    null -> null
    else -> inOrderTraversal(treeNode).foldRight(null as DoubleLinkedNode<T>?) { t, acc ->
      DoubleLinkedNode(t).apply {
        next = acc
        acc?.previous = this
      }
    }?.also { closeDoubleLinkedList(it) }
  }

  fun <T> inOrderTraversal(treeNode: BinaryTree<T>?, acc: MutableList<T> = mutableListOf()): MutableList<T> = when (treeNode) {
    null -> acc
    else -> inOrderTraversal(treeNode.right, inOrderTraversal(treeNode.left, acc).apply { add(treeNode.value) })
  }


  fun <T> closeDoubleLinkedList(node:DoubleLinkedNode<T>){
    var current = node
    while (current.next != null) current = current.next!!
    current.next = node
    node.previous = current
  }

  data class DoubleLinkedNode<T>(val value: T, var next: DoubleLinkedNode<T>? = null, var previous: DoubleLinkedNode<T>? = null)
}


fun main() {
  val converter = ConvertBinaryTreeSearchToDoubleLinkedList()
  listOf(
      BinaryTree(3,
          left = BinaryTree(9),
          right = BinaryTree(20, left = BinaryTree(15), right = BinaryTree(7)))
  ).map { treeNode ->
    val tree = converter.execute(treeNode)
    var current = tree
    while (current != null) {
      print("${current.value} ,")
      current = current.next
      if (current == tree) break
    }
    println()
    current = tree
    while (current != null) {
      print("${current.value} ,")
      current = current.previous
      if (current == tree) break
    }
  }
}