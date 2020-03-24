package io.trie

data class TrieNode(
    val value: Char?,
    val path: String,
    var finishWord: Boolean = false,
    val children: MutableList<TrieNode> = mutableListOf()) {

  fun add(child: TrieNode) = children.add(child)

  fun getOrAdd(char: Char, finishWord: Boolean = false) = children.firstOrNull { it.value == char }?.also {
    it.finishWord = it.finishWord || finishWord
  }
      ?: TrieNode(char, this.path + char, finishWord).also { children.add(it) }

  fun searchRegex(word: String): Boolean {
    var current: List<TrieNode> = listOf(this)
    word.forEach { char ->
      if (current.isEmpty()) return false
      current = when (char) {
        '.' -> current.flatMap { it.children }
        else -> current.flatMap { it.children }.filter { it.value == char }
      }
    }
    return current.any { it.finishWord }
  }

  fun search(word: String): Boolean = searchRegex(word)

  fun insert(word: String) = word.fold(this) { acc, char -> acc.getOrAdd(char) }.apply { finishWord = true }

  fun getOrNull(char: Char) = children.firstOrNull { it.value == char }

  companion object {
    fun root() = TrieNode(null, "")
  }
}