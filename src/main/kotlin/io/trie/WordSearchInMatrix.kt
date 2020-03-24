package io.trie

// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1056/
class WordSearchInMatrix {

  fun execute(board: Array<CharArray>, words: Array<String>): List<String> {
    val root = TrieNodeW.root().also { it.addWords(words) }
    val result = mutableListOf<String>()
    val wordsList = words.toList()
    board.indices.map { i ->
      if (!result.containsAll(wordsList)) {
        board[i].indices.map { j ->
          if (!result.containsAll(wordsList)) {
            find(board, i, j, root, result)
          }
        }
      }
    }
    return result
  }

  private fun find(board: Array<CharArray>, i: Int, j: Int, node: TrieNodeW, acc: MutableList<String>): Unit = when {
    i in board.indices && j in board.first().indices && board[i][j] != '#' && node.children.any { it.value == board[i][j] } -> {
      val childNode = node.children.first { it.value == board[i][j] }
      childNode.isWord?.let { word ->
        childNode.isWord = null
        acc.add(word)
      }
      val temp = board[i][j]
      board[i][j] = '#'
      find(board, i + 1, j, childNode, acc)
      find(board, i - 1, j, childNode, acc)
      find(board, i, j + 1, childNode, acc)
      find(board, i, j - 1, childNode, acc)
      board[i][j] = temp
    }
    else -> {
    }
  }

  private data class TrieNodeW(
      val value: Char?,
      var isWord: String? = null,
      val children: MutableList<TrieNodeW> = mutableListOf()) {

    fun getOrAdd(char: Char) = children.firstOrNull { it.value == char }
        ?: TrieNodeW(char).also { children.add(it) }

    fun addWords(words: Array<String>) = words.map { addWord(it) }

    fun addWord(word: String) = word.fold(this) { acc, char -> acc.getOrAdd(char) }.also { it.isWord = word }

    companion object {
      fun root() = TrieNodeW(null)
    }
  }


}

private data class Input(val board: Array<CharArray>, val words: List<String>, val output: Set<String>)

fun main() {
  val wordSearchInMatrix = WordSearchInMatrix()
  listOf(
      Input(arrayOf(
          charArrayOf('o', 'a', 'a', 'n'),
          charArrayOf('e', 't', 'a', 'e'),
          charArrayOf('i', 'h', 'k', 'r'),
          charArrayOf('i', 'f', 'l', 'v')
      ), listOf("oath", "pea", "eat", "rain"), setOf("eat", "oath")),
      Input(arrayOf(
          charArrayOf('o', 'a'),
          charArrayOf('e', 't')
      ), listOf("tea", "oat", "ta", "teo", "oate"), setOf("oat", "ta", "teo", "oate")),
      Input(arrayOf(
          charArrayOf('b', 'b', 'a', 'a', 'b', 'a'),
          charArrayOf('b', 'b', 'a', 'b', 'a', 'a'),
          charArrayOf('b', 'b', 'b', 'b', 'b', 'b'),
          charArrayOf('a', 'a', 'a', 'b', 'a', 'a'),
          charArrayOf('a', 'b', 'a', 'a', 'b', 'b')
      ), listOf("abbbababaa"), setOf("abbbababaa")
      )

  ).mapIndexed { index, (board, words, output) ->
    val result = wordSearchInMatrix.execute(board, words.toTypedArray()).toSet()
    val isValid = result == output
    val message = if (isValid) "Index $index result is valid" else "$index expected $output but got $result "
    println(message)
  }
}