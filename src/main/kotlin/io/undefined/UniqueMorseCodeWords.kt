package io.undefined

import io.utils.runTests


// https://leetcode.com/problems/unique-morse-code-words/
class UniqueMorseCodeWords {
  val map = mapOf(
      'a' to ".-",
      'b' to "-...",
      'c' to "-.-.",
      'd' to "-..",
      'e' to ".",
      'f' to "..-.",
      'g' to "--.",
      'h' to "....",
      'i' to "..",
      'j' to ".---",
      'k' to "-.-",
      'l' to ".-..",
      'm' to "--",
      'n' to "-.",
      'o' to "---",
      'p' to ".--.",
      'q' to "--.-",
      'r' to ".-.",
      's' to "...",
      't' to "-",
      'u' to "..-",
      'v' to "...-",
      'w' to ".--",
      'x' to "-..-",
      'y' to "-.--",
      'z' to "--.."
  )

  fun execute(words: Array<String>): Int =
      words.fold(mutableSetOf<String>()) { acc, word ->
      acc.apply {
        add(word.map { map.getValue(it) }.joinToString(separator = "") { it })
      }
    }.size
}

fun main() {
  runTests(listOf(
      arrayOf("gin", "zen", "gig", "msg") to 2
  )) { (input, value) -> value to UniqueMorseCodeWords().execute(input) }
}