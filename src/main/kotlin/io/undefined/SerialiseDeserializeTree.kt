package io.undefined

import io.models.TreeNode
import io.utils.runTests

class SerialiseDeserializeTree {

  // Encodes a URL to a shortened URL.
  fun serialize(root: TreeNode?): String {
    if (root == null) return ""
    val result = StringBuilder()
    var stack = listOf(Triple(0, root, "*"))
    while (stack.isNotEmpty()) {
      var first = true
      var pos = 0
      stack = stack.flatMap { (position, tree, side) ->
        result.append("${if (first) "" else ","}$position#${tree.`val`}#$side")
        first = false
        when {
          tree.left != null && tree.right != null -> listOf(tree.generateLeft(pos), tree.generateRight(pos))
          tree.left != null -> listOf(tree.generateLeft(pos))
          tree.right != null -> listOf(tree.generateRight(pos))
          else -> emptyList()
        }.also { pos++ }
      }
      result.append("|")
    }
    return result.toString()
  }

  private fun TreeNode.generateLeft(position: Int) = Triple(position, this.left!!, "l")
  private fun TreeNode.generateRight(position: Int) = Triple(position, this.right!!, "r")

  // Decodes your encoded data to tree.
  fun deserialize(data: String): TreeNode? {
    println(data)
    if (data.isEmpty())
      return null
    val commaRegex = ",".toRegex()
    val floorRegex = "\\|".toRegex()
    val numbersSeparator = "#".toRegex()
    var result: TreeNode? = null
    var previous = emptyList<TreeNode>()
    floorRegex.split(data).map { floor ->
      val next = mutableListOf<TreeNode>()
      commaRegex.split(floor).filter { it.isNotEmpty() }.forEach { pairString ->
        val (index, second, isLeft) = numbersSeparator.split(pairString).filter { it.isNotEmpty() }.let { (p, f, s) -> Triple(p.toInt(), f.toInt(), s == "l") }
        val treeNode = TreeNode(second)
        when {
          previous.isEmpty() -> result = treeNode
          isLeft -> previous[index].left = treeNode
          else -> previous[index].right = treeNode
        }
        next.add(treeNode)
      }
      previous = next
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      TreeNode(1,
          left = TreeNode(2, TreeNode(4, TreeNode(8))),
          right = TreeNode(3, TreeNode(6, TreeNode(12)), TreeNode(7, right = TreeNode(15, TreeNode(16), TreeNode(17)))))
  )) { input -> input to SerialiseDeserializeTree().let { it.deserialize(it.serialize(input)) } }
}