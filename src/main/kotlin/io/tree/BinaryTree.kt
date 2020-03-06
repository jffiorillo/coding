package io.tree

data class BinaryTree<T>(val value: T, val left: BinaryTree<T>? = null, val right: BinaryTree<T>? = null)

fun binaryTreeNumbers() = listOf(
    BinaryTree(1, right = BinaryTree(2, left = BinaryTree(3))),
    BinaryTree(1)
)

fun binaryTreeLetters() = listOf(
    BinaryTree("F",
        left = BinaryTree("B",
            left = BinaryTree("A"),
            right = BinaryTree("D",
                left = BinaryTree("C"),
                right = BinaryTree("E"))),
        right = BinaryTree("G",
            right = BinaryTree("I",
                left = BinaryTree("H")
            )
        )
    )
)

fun symmetricTree() = listOf(
    BinaryTree(1,
        left = BinaryTree(2,
            left = BinaryTree(3),
            right = BinaryTree(4)),
        right = BinaryTree(2,
            left = BinaryTree(4),
            right = BinaryTree(3))

    )
)

fun runTraversalTree(traversalTree: TraversalTree) {
  println("${traversalTree.javaClass}")
  binaryTreeNumbers().map { tree -> run(traversalTree, tree) }
  binaryTreeLetters().map { tree -> run(traversalTree, tree) }
}

fun <T> run(traversalTree: TraversalTree, tree: BinaryTree<T>) {
  val executeRecursive = traversalTree.executeRecursive(tree, mutableListOf())
  println("executeRecursive $executeRecursive")
  val executeIterative = traversalTree.executeIterative(tree)
  println("executeIterative $executeIterative")
  assert(executeIterative == executeRecursive)
}