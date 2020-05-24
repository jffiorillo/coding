package io.queue

import io.utils.runTests
import java.util.*

// https://leetcode.com/problems/simplify-path/
class SimplyPath {

  fun execute(input: String): String {
    val queue = LinkedList<String>()
    input.split("/").forEach { dir ->
      when {
        dir == "." || dir.isEmpty() -> {
        }
        dir == ".." -> if (queue.isNotEmpty()) queue.removeLast()
        else -> queue.addLast(dir)
      }
    }
    return queue.joinToString(separator = "/", prefix = "/") { it }
  }
}

fun main() {
  runTests(listOf(
      "/home//foo/" to "/home/foo",
      "/a/./b/../../c/" to "/c",
      "/a//b////c/d//././/.." to "/a/b/c"
  )) { (input, value) -> value to SimplyPath().execute(input) }
}