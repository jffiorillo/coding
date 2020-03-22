package io.trie

import java.util.*

// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1058/
class MapSumPairs {
  private val root = TrieNodeSum(null, "")

  fun insert(key: String, value: Int) {
    var current = root
    key.forEach { char ->
      current = current.getOrAdd(char)
    }
    current.value = value
    current.finishWord = true
  }

  fun sum(prefix: String): Int {
    var current: TrieNodeSum? = root
    prefix.forEach { char ->
      current = current?.getOrNull(char)
    }
    return current?.values() ?: 0
  }

  private data class TrieNodeSum(
      val charValue: Char?,
      val path: String,
      var finishWord: Boolean = false,
      var value: Int = 0,
      val children: MutableList<TrieNodeSum> = mutableListOf()) {

    fun values(): Int{
      var result = 0
      val stack = LinkedList(listOf(this))
      while (stack.isNotEmpty()) {
        val current = stack.pop()
        if (current.finishWord) {
          result += current.value
        }
        stack.addAll(current.children)
      }
      return result
    }

    fun add(child: TrieNodeSum) = children.add(child)

    fun getOrAdd(char: Char, finishWord: Boolean = false) = children.firstOrNull { it.charValue == char }?.also {
      it.finishWord = it.finishWord || finishWord
    }
        ?: TrieNodeSum(char, this.path + char, finishWord).also { children.add(it) }

    fun getOrNull(char: Char) = children.firstOrNull { it.charValue == char }
  }

//  val values = mutableListOf<Pair<String, Int>>()
//
//  fun insert(key: String, value: Int) {
//    values.firstOrNull { it.first == key }?.let { values.remove(it) }
//    values.add(key to value)
//  }
//
//  fun sum(prefix: String): Int = values.filter { it.first.startsWith(prefix) }.fold(0) { acc, item -> acc + item.second }
}

fun main() {
  val mapSumPairs = MapSumPairs()
  mapSumPairs.insert("apple", 3)
  println(mapSumPairs.sum("ap"))
  mapSumPairs.insert("app", 2)
  println(mapSumPairs.sum("ap"))
}