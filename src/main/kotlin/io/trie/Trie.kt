package io.trie


// https://leetcode.com/explore/learn/card/trie/147/basic-operations/1047/
class Trie {

  private data class TrieNode(
      val value: Char?,
      val path: String,
      var finishWord: Boolean = false,
      val children: MutableList<TrieNode> = mutableListOf()) {
    fun add(child: TrieNode) = children.add(child)

    fun getOrAdd(char: Char, finishWord: Boolean = false) = children.firstOrNull { it.value == char }?.also {
      it.finishWord = it.finishWord || finishWord
    }
        ?: TrieNode(char, this.path + char, finishWord).also { children.add(it) }

    fun getOrNull(char: Char) = children.firstOrNull { it.value == char }
  }

  private val root = TrieNode(null, "")


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