package io.tree

import java.util.*

// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995/
class BinaryTreeSerializer {


  private val separator = "|"
  private val itemSeparator = ","
  private val nullValue = "^"

  fun execute(tree: BinaryTree<String>): String {
    val stack = Stack<List<BinaryTree<String>?>>()
    val result = mutableListOf<List<String?>>()
    stack.push(listOf(tree))
    while (stack.isNotEmpty()) {
      val current = stack.pop()
      result.add(current.map { it?.value })
      current.flatMap { listOf(it?.left, it?.right) }.let { news ->
        if (news.filterNotNull().isNotEmpty()) stack.push(news)
      }
    }
    return result.joinToString(separator = separator) { it.toString().replace("[", "").replace("]", "").replace(" ", "").replace("null", nullValue) }
  }

  fun execute(serialize: String): BinaryTree<String>? =
      serialize.split(separator).reversed().fold(listOf<BinaryTree<String>?>()) { acc, value ->
        transformCharacters(value, if (acc.isEmpty()) null else acc.iterator())
      }.first()

  private fun transformCharacters(value: String, children: Iterator<BinaryTree<String>?>?): List<BinaryTree<String>?> =
      value.split(itemSeparator).map {
        if (it == nullValue) {
          repeat(2) { children?.next() }
          null
        } else BinaryTree(it, children?.next(), children?.next())
      }

}

fun main() {
  val binaryTreeSerializer = BinaryTreeSerializer()
  val tree = binaryTreeLetters().first()
  val serialize = binaryTreeSerializer.execute(tree)
  val deserialize = binaryTreeSerializer.execute(serialize)
  println("deserialize is correct ${tree == deserialize}")
}