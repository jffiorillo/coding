package io.array

import kotlin.math.absoluteValue

// https://leetcode.com/problems/asteroid-collision/
class AsteroidCollision {

  fun execute(asteroids: IntArray): IntArray = mutableListOf<Int>().apply {
    asteroids.forEach { value ->
      when {
        isEmpty() -> add(value)
        last().collide(value) -> fixCollection(value)
        else -> add(value)
      }
    }
  }.toIntArray()

  private fun Int.collide(rightAsteroid: Int): Boolean = this > 0 && rightAsteroid < 0

  private fun MutableList<Int>.fixCollection(second: Int) {
    loop@ while (isNotEmpty()) {
      when {
        last() < 0 -> {
          add(second)
          break@loop
        }
        last() > second.absoluteValue -> break@loop
        last() == second.absoluteValue -> {
          removeAt(size - 1)
          break@loop
        }
        else -> {
          removeAt(size - 1)
          if (isEmpty()) {
            add(second)
            break@loop
          }
        }
      }
    }
  }
}

fun main() {
  val asteroidCollision = AsteroidCollision()
  listOf(
      intArrayOf(10, 2, -5) to listOf(10),
      intArrayOf(8, -8) to emptyList(),
      intArrayOf(-2, -2, 1, -2) to listOf(-2, -2, -2),
      intArrayOf(1, -2, -2, -2) to listOf(-2, -2, -2),
      intArrayOf(1, -1, -2, -2) to listOf(-2, -2)
  ).forEachIndexed { index, (input, value) ->
    val output = asteroidCollision.execute(input).toList()
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}