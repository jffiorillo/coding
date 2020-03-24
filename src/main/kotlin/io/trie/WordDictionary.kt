package io.trie

// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1052/
class WordDictionary {

  private val root = TrieNode(null, "")

  /** Adds a word into the data structure. */
  fun addWord(word: String) {
    root.insert(word)
  }

  fun search(word: String): Boolean = root.searchRegex(word)
}

fun main() {

  val wordDictionary = WordDictionary()
  wordDictionary.addWord("bad")
  wordDictionary.addWord("dad")
  wordDictionary.addWord("mad")
  println(wordDictionary.search("pad"))
  println(wordDictionary.search("bad"))
  println(wordDictionary.search(".ad"))
  println(wordDictionary.search("b.."))
}