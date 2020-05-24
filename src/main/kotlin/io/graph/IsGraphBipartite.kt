@file:Suppress("unused")

package io.graph

import java.util.*


// https://leetcode.com/problems/is-graph-bipartite/
class IsGraphBipartite {

  fun execute(input: Array<IntArray>): Boolean {
    // 0(not meet), 1(black), 2(white)
    val visited = IntArray(input.size)
    input.forEachIndexed { index, value ->
      if (value.isNotEmpty() && visited[index] == 0) {
        visited[index] = 1
        val queue = LinkedList<Int>().apply { offer(index) }
        while (queue.isNotEmpty()) {
          val current = queue.poll()
          input[current].forEach { elem ->
            when (visited[elem]) {
              0 -> {
                visited[elem] = if (visited[current] == 1) 2 else 1
                queue.offer(elem)
              }
              visited[current] -> return false
            }
          }
        }
      }
    }
    return true
  }
}