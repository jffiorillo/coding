package io.queue

import io.utils.createActorWithResult
import kotlinx.coroutines.CoroutineScope
import java.util.*
import kotlin.collections.HashSet


//https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1375/
class OpenTheLock {

  private val max = 10
  private val initial = "0000"
  private val error = -1

  fun execute(deadEnds: Array<String>, target: String): Int = when {
    deadEnds.contains(initial) -> error
    target == initial -> 0
    else -> {
      val stack = Stack<List<String>>()
      stack.push(listOf(initial))
      var steps = 0
      val visited = HashSet<String>()
      while (stack.isNotEmpty()) {
//        createActorWithResult(stack.pop()){receiveChannel, sendChannel ->
//
//        }
        stack.pop().flatMap { current ->
          if (current == target) return steps
          visited.add(current)
          current.generateNextGeneration(deadEnds, visited)
        }.let { nextGeneration -> stack.add(nextGeneration) }
        steps += 1
      }
      error
    }
  }

  fun String.generateNextGeneration(deadEnds: Array<String>, visited: HashSet<String>): List<String> = this.mapIndexed { index, char ->
    char.toString().toInt().generateNextNumbers().map { newValue ->
      this.repeat(1).replaceRange(index, kotlin.math.min(index + 1, 4), newValue.toString())
    }
  }.flatten().filter { !deadEnds.contains(it) && !visited.contains(it) }


  private fun Int.generateNextNumbers() = when (this) {
    0 -> listOf(9, 1)
    else -> listOf((this + 1).rem(max), this - 1)
  }
}

fun main() {
  val openTheLock = OpenTheLock()
  listOf(
      Help(deadEnds = arrayOf("0201", "0101", "0102", "1212", "2002"), target = "0202", output = 6),
      Help(deadEnds = arrayOf("8888"), target = "0009", output = 1),
      Help(deadEnds = arrayOf("8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"), target = "8888", output = -1)
  ).mapIndexed { index, item ->
    val output = openTheLock.execute(item.deadEnds, item.target)
    if (output != item.output) {
      println("error with $item wrong output $output")
    } else println("done with $index")
  }
}

private data class Help(val deadEnds: Array<String>, val target: String, val output: Int)