package io.queue

private const val empty = -1

// https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1337/
class CircularQueue<T>(private val maxSize: Int) {

  private val internalQueue = mutableListOf<T>()
  private var head: Int = empty
  private var tail: Int = empty


  /** Insert an element into the circular queue. Return true if the operation is successful. */
  fun enQueue(value: T): Boolean {
    if (isFull()) return false
    if (isEmpty()) {
      head = 0
    }
    tail = (tail + 1).rem(maxSize)
    if (internalQueue.size == maxSize) internalQueue[tail] = value
    else internalQueue.add(tail, value)
    return true
  }

  /** Delete an element from the circular queue. Return true if the operation is successful. */
  fun deQueue(): Boolean {
    if (isEmpty()) return false
    if (head == tail) {
      head = empty
      tail = empty
      internalQueue.clear()
    } else {
      head = (head + 1).rem(maxSize)
    }
    return true
  }

  /** Get the front item from the queue. */
  fun front(): T? = if (head == empty) null else internalQueue[head]

  /** Get the last item from the queue. */
  fun rear(): T? = if (tail == empty) null else internalQueue[tail]

  /** Checks whether the circular queue is empty or not. */
  fun isEmpty(): Boolean = head == empty

  /** Checks whether the circular queue is full or not. */
  fun isFull(): Boolean = head == (tail + 1).rem(maxSize)
}

fun main() {
  CircularQueue<Int>(30).apply {
//    println("enQueue(4) ${enQueue(4)}")
//    println("enQueue(1) ${enQueue(1)}")
//    println("enQueue(2) ${enQueue(2)}")
//    println("enQueue(3) ${enQueue(3)}")
//    println("rear() ${rear()}")
//    println("enQueue(9) ${enQueue(9)}")
//    println("deQueue() ${deQueue()}")
//    println("front() ${front()}")
//    println("deQueue() ${deQueue()}")
//    println("deQueue() ${deQueue()}")
//    println("isEmpty() ${isEmpty()}")
//    println("deQueue() ${deQueue()}")
////    println("isFull() ${isFull()}")
//    println("enQueue(6) ${enQueue(6)}")
//    println("enQueue(4) ${enQueue(4)}")
//    println("rear() ${rear()}")
//    println("MyCircularQueue(30) ${MyCircularQueue(30)}")
    println("enQueue(71) ${enQueue(71) == true}")
    println("isFull() ${isFull() == false}")
    println("enQueue(32) ${enQueue(32) == true}")
    println("enQueue(1) ${enQueue(1) == true}")
    println("isFull() ${isFull() == false}")
    println("enQueue(32) ${enQueue(32) == true}")
    println("enQueue(8) ${enQueue(8) == true}")
    println("enQueue(6) ${enQueue(6) == true}")
    println("front() ${front() == 71}")
    println("front() ${front() == 71}")
    println("rear() ${rear() == 6}")
    println("enQueue(8) ${enQueue(8) == true}")
    println("rear() ${rear() == 8}")
    println("enQueue(3) ${enQueue(3) == true}")
    println("rear() ${rear() == 3}")
    println("front() ${front() == 71}")
    println("enQueue(56) ${enQueue(56) == true}")
    println("enQueue(41) ${enQueue(41) == true}")
    println("front() ${front() == 71}")
    println("enQueue(14) ${enQueue(14) == true}")
    println("enQueue(6) ${enQueue(6) == true}")
    println("rear() ${rear() == 6}")
    println("enQueue(25) ${enQueue(25) == true}")
    println("isEmpty() ${isEmpty() == false}")
    println("rear() ${rear() == 25}")
    println("front() ${front() == 71}")
    println("rear() ${rear() == 25}")
    println("enQueue(44) ${enQueue(44) == true}")
    println("front() ${front() == 71}")
    println("enQueue(84) ${enQueue(84) == true}")
    println("rear() ${rear() == 84}")
    println("isEmpty() ${isEmpty() == false}")
    println("rear() ${rear() == 84}")
    println("enQueue(59) ${enQueue(59) == true}")
    println("front() ${front() == 71}")
    println("front() ${front() == 71}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(4) ${enQueue(4) == true}")
    println("front() ${front() == 32}")
    println("enQueue(40) ${enQueue(40) == true}")
    println("enQueue(11) ${enQueue(11) == true}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(94) ${enQueue(94) == true}")
    println("isFull() ${isFull() == false}")
    println("front() ${front() == 1}")
    println("enQueue(72) ${enQueue(72) == true}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(19) ${enQueue(19) == true}")
    println("isEmpty() ${isEmpty() == false}")
    println("isEmpty() ${isEmpty() == false}")
    println("enQueue(20) ${enQueue(20) == true}")
    println("front() ${front() == 32}")
    println("front() ${front() == 32}")
    println("rear() ${rear() == 20}")
    println("deQueue() ${deQueue() == true}")
    println("front() ${front() == 8}")
    println("enQueue(58) ${enQueue(58) == true}")
    println("rear() ${rear() == 58}")
    println("enQueue(54) ${enQueue(54) == true}")
    println("rear() ${rear() == 54}")
    println("rear() ${rear() == 54}")
    println("front() ${front() == 8}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(65) ${enQueue(65) == true}")
    println("deQueue() ${deQueue() == true}")
    println("rear() ${rear() == 65}")
    println("enQueue(59) ${enQueue(59) == true}")
    println("front() ${front() == 8}")
    println("enQueue(26) ${enQueue(26) == true}")
    println("enQueue(10) ${enQueue(10) == true}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(14) ${enQueue(14) == true}")
    println("front() ${front() == 3}")
    println("enQueue(2) ${enQueue(2) == true}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(37) ${enQueue(37) == true}")
    println("front() ${front() == 56}")
    println("front() ${front() == 56}")
    println("enQueue(46) ${enQueue(46) == true}")
    println("enQueue(63) ${enQueue(63) == true}")
    println("enQueue(42) ${enQueue(42) == true}")
    println("front() ${front() == 56}")
    println("enQueue(84) ${enQueue(84) == true}")
    println("enQueue(30) ${enQueue(30) == true}")
    println("rear() ${rear() == 30}")
    println("deQueue() ${deQueue() == true}")
    println("enQueue(49) ${enQueue(49) == true}")
    println("front() ${front() == 41}")
    println("enQueue(79) ${enQueue(79) == true}")
    println("enQueue(46) ${enQueue(46) == false}")
    println("rear() ${rear() == 79}")
    println("enQueue(97) ${enQueue(97) == false}")
    println("enQueue(83) ${enQueue(83) == false}")
    println("rear() ${rear() == 79}")
    println("isFull() ${isFull() == true}")
    println("enQueue(76) ${enQueue(76) == false}")
    println("rear() ${rear() == 79}")
    println("enQueue(79) ${enQueue(79) == false}")
    println("deQueue() ${deQueue() == true}")
    println("rear() ${rear() == 79}")
    println("enQueue(44) ${enQueue(44) == true}")
  }

}