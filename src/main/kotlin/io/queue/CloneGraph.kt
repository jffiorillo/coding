package io.queue

// https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/1392/
class CloneGraph {

  fun execute(node: Node?): Node? =
      if (node == null) null else clone(node, arrayOfNulls(100))


  private fun clone(node: Node, visited: Array<Node?>): Node? {
    if (visited[node.`val` - 1] != null) {
      return visited[node.`val` - 1]
    }
    val newNode = Node(node.`val`)
    visited[newNode.`val` - 1] = newNode
    for (neighbor in node.neighbors) {
      newNode.neighbors.add(clone(neighbor, visited))
    }
    return newNode
  }
}