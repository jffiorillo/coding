package io.trie


// https://leetcode.com/explore/learn/card/trie/147/basic-operations/1047/
class Trie {

  private val root = TrieNode.root()


  /** Inserts a word into the trie. */
  fun insert(word: String) {
    var current = root
    word.forEach { char ->
      current = current.getOrAdd(char)
    }
    current.finishWord = true
  }

  /** Returns if the word is in the trie. */
  fun search(word: String): Boolean {
    var current: TrieNode? = root
    word.forEach { char ->
      current = current?.getOrNull(char) ?: return false
    }
    return current?.finishWord == true
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  fun startsWith(prefix: String): Boolean {
    var current: TrieNode? = root
    prefix.forEach { char ->
      current = current?.getOrNull(char) ?: return false
    }
    return current != null
  }

}

fun main() {

  val trie = Trie()
  val word = "apple"
  trie.insert(word)
  trie.search(word)
}