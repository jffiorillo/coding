@file:Suppress("unused")

package io.undefined

// https://leetcode.com/problems/flatten-nested-list-iterator/
class FlattenNestedListOperator(nestedList: List<NestedInteger>) {

  private val flatten: List<Int>
  private var current = 0

  init {
    fun getValue(nestedInteger: NestedInteger): List<Int> =
        when (nestedInteger.isInteger()) {
          true -> listOf(nestedInteger.getInteger()!!)
          false -> nestedInteger.getList()!!.flatMap { getValue(it) }
        }

    flatten = nestedList.flatMap { getValue(it) }
  }

  fun next(): Int = flatten[current++]

  fun hasNext(): Boolean = current < flatten.size

}


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
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