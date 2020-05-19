@file:Suppress("unused")

package io.graph

import java.util.*

// https://leetcode.com/problems/nested-list-weight-sum/
class NestedListWeightSum {

  interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int?

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int): Unit

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger): Unit

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>?
  }


  fun execute(input: List<NestedInteger>): Int {
    val stack = LinkedList<Pair<Int, NestedInteger>>().apply { input.forEach { this.push(1 to it) } }
    var result = 0
    while (stack.isNotEmpty()) {
      stack.pop().let { (depth, value) ->
        when (value.isInteger()) {
          true -> result += depth * value.getInteger()!!
          else -> stack.addAll(value.getList()!!.map { depth + 1 to it })
        }
      }
    }
    return result
  }
}