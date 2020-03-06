package io.tree

//https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/942/
class ConstructFromInOderAndPostOrder {
  fun <T> execute(inOrder: List<T>, postOrder: List<T>): BinaryTree<T>? = when {
    inOrder.isEmpty() -> null
    inOrder.size == 1 -> BinaryTree(inOrder.first())
    else -> {
      val root = postOrder.last()
      val index = inOrder.indexOf(root)
      val leftList = inOrder.subList(0, index)
      val rightList = inOrder.subList(index + 1, inOrder.size)
      BinaryTree(root,
          left = execute(leftList, postOrder.filter { leftList.contains(it) }),
          right = execute(rightList, postOrder.filter { rightList.contains(it) }))
    }
  }
}


fun main() {
  val constructBinaryTree = ConstructFromInOderAndPostOrder()

  listOf(
      InfoWrapper(listOf(9, 3, 15, 20, 7), listOf(9, 15, 7, 20, 3),
          BinaryTree(3,
              left = BinaryTree(9),
              right = BinaryTree(20, left = BinaryTree(15), right = BinaryTree(7))))
  ).map { (inOrder, postOrder, value) ->
    println("${constructBinaryTree.execute(inOrder, postOrder) == value}")
  }
}

private data class InfoWrapper<T>(val inOrder: List<T>, val postOrder: List<T>, val value: BinaryTree<T>)