package io.queue

import kotlin.math.absoluteValue
import kotlin.math.min

//https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1360/
class MinStack {

  /** initialize your data structure here. */
  private val list = mutableListOf<Pair<Int, Int>>()

  fun push(x: Int) {
    if (list.isEmpty()) list.add(Pair(x, x))
    else list.add(0,Pair(x, min(x, list[0].second)))
  }

  fun pop() {
    if (list.isNotEmpty()) list.removeAt(0)
  }

  fun top(): Int = list[0].first

  fun getMin(): Int = list[0].second

}

fun main(){
  println("${min(-2,-3)}")
}