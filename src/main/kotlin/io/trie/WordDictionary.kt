package io.trie

// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1052/
class WordDictionary {

  private val root = TrieNode(null, "")

  /** Adds a word into the data structure. */
  fun addWord(word: String) {
    root.insert(word)
  }

  fun search(word: String): Boolean = root.searchRegex(word)

  private data class TrieNode(
      val char: Char?,
      val path: String,
      var finishWord: Boolean = false,
      val children: MutableList<TrieNode> = mutableListOf()
  ) {
    fun getOrAdd(char: Char, finishWord: Boolean = false) =
        children.firstOrNull { it.char == char }?.also { it.finishWord = it.finishWord || finishWord }
            ?: TrieNode(char, this.path + char, finishWord).also { children.add(it) }

    fun insert(word: String) =
        word.fold(this) { acc, char -> acc.getOrAdd(char) }.apply { finishWord = true }

    /** Returns if the word is in the trie. */
    fun searchRegex(word: String): Boolean {
      var current: List<TrieNode> = listOf(this)
      word.forEach { char ->
        if (current.isEmpty()) return false
        current = when (char) {
          '.' -> current.flatMap { it.children }
          else -> current.flatMap { it.children }.filter { it.char == char }
        }
      }
      return current.any { it.finishWord }
    }
  }
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