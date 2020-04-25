package io.queue

import io.utils.runTests
import java.util.*


// https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3309/
// https://leetcode.com/problems/lru-cache/
class LRUCache(private val capacity: Int) {
  private val cache = Hashtable<Int, DLinkedNode>()
  private var count = 0
  private val head = DLinkedNode()
  private val tail = DLinkedNode().apply { pre = head }

  init {
    head.apply {
      post = tail
    }
  }

  operator fun get(key: Int): Int =
      cache[key]?.let { node ->
        // move the accessed node to the head;
        moveToHead(node)
        node.value
      } ?: -1

  fun put(key: Int, value: Int) {
    val node = cache[key]
    if (node == null) {
      val newNode = DLinkedNode(key, value)
      cache[key] = newNode
      addNode(newNode)
      count++
      if (count > capacity) {
        cache.remove(popTail().key)
        count--
      }
    } else {
      // update the value.
      node.value = value
      moveToHead(node)
    }
  }

  /**
   * Always add the new node right after head;
   */
  private fun addNode(node: DLinkedNode) {
    node.pre = head
    node.post = head.post
    head.post!!.pre = node
    head.post = node
  }

  /**
   * Remove an existing node from the linked list.
   */
  private fun removeNode(node: DLinkedNode) {
    val pre = node.pre!!
    val post = node.post!!
    pre.post = post
    post.pre = pre
  }

  /**
   * Move certain node in between to the head.
   */
  private fun moveToHead(node: DLinkedNode) {
    removeNode(node)
    addNode(node)
  }

  /**
   * Pop the current tail.
   */
  private fun popTail(): DLinkedNode = tail.pre!!.also { res -> removeNode(res) }

  private data class DLinkedNode(var key: Int = 0, var value: Int = 0, var pre: DLinkedNode? = null, var post: DLinkedNode? = null)
}

fun main() {
  runTests(listOf(
      {
        val lruCache = LRUCache(2)
        lruCache.put(1, 1)
        lruCache.put(2, 2)
        println(lruCache[1] == 1)
        lruCache.put(3, 3)
        println(lruCache[2] == -1)
        lruCache.put(4, 4)
        println(lruCache[1] == -1)
        println(lruCache[3] == 3)
        lruCache[4]
      } to 4,
      {
        val lruCache = LRUCache(2)
        println(lruCache[2] == -1)
        lruCache.put(2, 6)
        lruCache[1]
        lruCache.put(1, 5)
        lruCache.put(1, 2)
        lruCache[1]
        lruCache[2]
      } to 6,
      {
        val lruCache = LRUCache(2)
        lruCache.put(2, 1)
        lruCache.put(1, 1)
        lruCache.put(2, 3)
        lruCache.put(4, 1)
        println(lruCache[1] == -1)
        lruCache[2]
      } to 3,
      {
        val lruCache = LRUCache(3)
        lruCache.put(1, 1)
        lruCache.put(2, 2)
        lruCache.put(3, 3)
        lruCache.put(4, 4)
        println(lruCache[4] == 4)
        println(lruCache[3] == 3)
        println(lruCache[2] == 2)
        println(lruCache[1] == -1)
        lruCache.put(5, 5)
        println(lruCache[1] == -1)
        println(lruCache[2] == 2)
        println(lruCache[3] == 3)
        println(lruCache[4] == -1)
        lruCache[5]
      } to 5
  )) { (input, value) -> value to input() }
}