package io.queue

import java.util.*

//https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1393/
class FloodFill {

  fun execute(image: Array<IntArray>, x: Int, y: Int, newColor: Int): Array<IntArray> {
    val initialColor = image[x][y]
    if (initialColor == newColor) return image
    val stack = LinkedList<Coordinates>()
    stack.push(Coordinates(x, y))

    while (stack.isNotEmpty()) {
      stack.pop().let { coordinates ->
        if (image[coordinates.i][coordinates.j] == initialColor) {
          image[coordinates.i][coordinates.j] = newColor
          coordinates.generateChild(image.size, image[coordinates.i].size).filter { newCoordinate ->
            image[newCoordinate.i][newCoordinate.j] == initialColor
          }.map { stack.offer(it) }
        }
      }
    }

    return image
  }

  data class Info(val image: Array<IntArray>, val x: Int, val y: Int, val newColor: Int)
}

fun main() {
  val floodFill = FloodFill()

  listOf(
      FloodFill.Info(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)), 1, 1, 2),
      FloodFill.Info(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 1)), 1, 1, 1)
  )
      .map { (image, x, y, newColor) ->
        println("${floodFill.execute(image, x, y, newColor).map { it.toList() }.toList()}")
      }


}


