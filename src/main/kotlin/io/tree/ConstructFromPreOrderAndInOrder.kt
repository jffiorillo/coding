package io.tree

//https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/943/
class ConstructFromPreOrderAndInOrder {
  fun <T> execute(preOrder: List<T>, inOrder: List<T>): BinaryTree<T>? = when {
    inOrder.isEmpty() -> null
    inOrder.size == 1 -> BinaryTree(preOrder.first())
    else -> {
      val root = preOrder.first()
      val index = inOrder.indexOf(root)
      val leftList = inOrder.subList(0, index)
      val rightList = inOrder.subList(index + 1, inOrder.size)
      BinaryTree(root,
          left = execute(preOrder.filter { leftList.contains(it) }, leftList),
          right = execute(preOrder.filter { rightList.contains(it) }, rightList))
    }
  }
}

fun main() {
  val constructBinaryTree = ConstructFromPreOrderAndInOrder()
  listOf(
      Wrapper(listOf(3, 9, 20, 15, 7), listOf(9, 3, 15, 20, 7),
          BinaryTree(3,
              left = BinaryTree(9),
              right = BinaryTree(20, left = BinaryTree(15), right = BinaryTree(7))))
  ).map { (inOrder, postOrder, value) ->
    println("${constructBinaryTree.execute(inOrder, postOrder) == value}")
  }
}

private data class Wrapper<T>(val preOrder: List<T>, val inOrder: List<T>, val value: BinaryTree<T>)