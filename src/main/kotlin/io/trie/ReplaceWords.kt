package io.trie

// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1053/
class ReplaceWords {

  private data class TrieNode(
      val char: Char?,
      val path: String,
      var finishWord: Boolean = false,
      val children: MutableList<TrieNode> = mutableListOf()
  ) {
    fun getOrAdd(char: Char, finishWord: Boolean = false) =
        children.firstOrNull { it.char == char }?.also { it.finishWord = it.finishWord || finishWord }
            ?: TrieNode(char, this.path + char, finishWord).also { children.add(it) }

    fun insert(word: String) {
      word.fold(this) { acc, char -> acc.getOrAdd(char) }.finishWord = true
    }

    fun getOrNull(char: Char) = children.firstOrNull { it.char == char }

    /** Returns if the word is in the trie. */
    fun searchRoot(word: String): TrieNode? {
      var current: TrieNode? = this
      word.forEach { char ->
        current = current?.getOrNull(char) ?: return null
        if (current?.finishWord == true) return current
      }
      return null
    }
  }

  fun execute(dict: List<String>, sentence: String): String =
      TrieNode(null, "").let { root ->
        dict.map { root.insert(it) }
        sentence.split(" ").map { word ->
          root.searchRoot(word)?.path ?: word
        }.joinToString(separator = " ") { it }
      }

}