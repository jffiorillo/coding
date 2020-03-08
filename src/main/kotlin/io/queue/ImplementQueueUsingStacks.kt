package io.queue

import java.util.*

class ImplementQueueUsingStacks {

  private val stack = Stack<Int>()

  /** Push element x to the back of queue. */
  fun push(x: Int) {
    stack.push(x)
  }

  /** Removes the element from in front of queue and returns that element. */
  fun pop(): Int {
    val tempArray = generateArray()
    return stack.pop().also {
      tempArray.forEach { stack.push(it) }
    }
  }

  private fun generateArray() = IntArray(stack.size - 1).also { tempArray ->
    var index = tempArray.size
    while (index != 0) {
      tempArray[index - 1] = stack.pop()
      index--
    }
  }

  /** Get the front element. */
  fun peek(): Int {
    val tempArray = generateArray()
    return stack.pop().also { lastElement ->
      stack.push(lastElement)
      tempArray.forEach { stack.push(it) }
    }
  }

  /** Returns whether the queue is empty. */
  fun empty(): Boolean = stack.isEmpty()
}

fun main() {
  val queue = ImplementQueueUsingStacks()

  println("${queue.push(1)}")
  println("${queue.push(2)}")
  println("${queue.peek()}")
  println("${queue.pop()}")
  println("${queue.empty()}")
}