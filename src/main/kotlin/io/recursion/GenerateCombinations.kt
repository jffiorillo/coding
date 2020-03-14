package io.recursion

import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class GenerateCombinations {

  fun execute(maxNumber: Int, numbers: Int): List<List<Int>> = when (numbers) {
    1 -> (1..maxNumber).map { listOf(it) }
    else -> execute((1..maxNumber).toList(), max(1, numbers))
  }

  fun execute(values: List<Int>, numbers: Int, index: Int = 0, acc: List<List<Int>> = emptyList()): List<List<Int>> = when (index) {
    0 -> execute(values, numbers, index + 1, values.map { listOf(it) })
    numbers -> acc
    else -> {
      execute(values, numbers, index + 1,
          acc.map { item -> item.fold(0) { acc, next -> if (acc > next) acc else next } to item }
              .flatMap { (max, item) -> (values.filter { it > max }).map { item + it } }
      )
    }
  }
}

@ExperimentalTime
fun main() {
  val generateCombinations = GenerateCombinations()
  measureTime {
    generateCombinations.execute(20, 16).map {
      println("$it")
    }
  }.let { println("Time $it") }
}