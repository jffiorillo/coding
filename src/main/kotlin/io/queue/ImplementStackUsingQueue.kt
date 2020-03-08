package io.queue

import java.util.*

class ImplementStackUsingQueue {

  /** Initialize your data structure here. */
  private val list =  LinkedList<Int>()

  /** Push element x onto stack. */
  fun push(x: Int) = list.push(x)

  /** Removes the element on top of the stack and returns that element. */
  fun pop(): Int = list.removeAt(0)


  /** Get the top element. */
  fun top(): Int = list[0]

  /** Returns whether the stack is empty. */
  fun empty(): Boolean = list.isEmpty()
}