package io.trie

import java.util.*

// https://cheonhyangzhang.gitbooks.io/leetcode-solutions/303-range-sum-query---immutable/336-palindrome-pairs.html
class PalindromePairs {

  fun execute(words: Array<String>): List<List<Int>> {
    if (words.isEmpty()) return emptyList()
    val root = Node()
    val finalResult: MutableList<List<Int>> = mutableListOf()
    val selfPalindrome = getSelfPalindrome(words)
    words.mapIndexed { index, value ->
      if (value.isEmpty()) {
        // Pair with all self-palindrome.
        finalResult.addAll(
            selfPalindrome.flatMap { selfPalindromeWordIndex ->
              listOf(listOf(index, selfPalindromeWordIndex), listOf(selfPalindromeWordIndex, index))
            }
        )
      } else {
        root.insert(value.reversed(), index)
      }
    }
    words.mapIndexed { index, word ->
      finalResult.addAll(root.search(word, index).map { pairIndex -> listOf(index, pairIndex) })

    }
    return finalResult
  }

  @Suppress("ArrayInDataClass")
  internal data class Node(
      val children: Array<Node?> = arrayOfNulls(26),
      // Equals to -1 in default. If it is a word's end, it is the index of the word.
      var endWordId: Int = -1,
      // List of word indices such that nodes below can construct a palindrome.
      var belowPalindromeWordIds: MutableList<Int> = mutableListOf()
  ) {
    /****************** Trie-related  */
    fun search(word: String, index: Int): List<Int> {
      val wordIndices: MutableList<Int> = mutableListOf()
      var ptr = this
      for (i in word.indices) {
        val label = word[i] - 'a'
        if (ptr.endWordId > -1 && isPalindrome(word, i, word.length - 1)) {
          wordIndices.add(ptr.endWordId)
        }
        if (ptr.children[label] == null) {
          return wordIndices
        }
        ptr = ptr.children[label]!!
      }
      if (ptr.endWordId > -1 && ptr.endWordId != index) wordIndices.add(ptr.endWordId)
      if (ptr.belowPalindromeWordIds.isNotEmpty()) wordIndices.addAll(ptr.belowPalindromeWordIds)
      return wordIndices
    }

    private fun getOrAdd(char: Char) = children[char.toLabel()] ?: Node().also { this.children[char.toLabel()] = it }

    private fun Char.toLabel() = this - 'a'

    fun insert(word: String, wordIndex: Int) =
        word.fold(this) { acc, char -> acc.getOrAdd(char).also { if (isPalindrome(word, char.toLabel() + 1, word.length-1)) it.belowPalindromeWordIds.add(wordIndex) } }.also {
          it.endWordId = wordIndex
        }
  }
}

private fun getSelfPalindrome(words: Array<String>): List<Int> =
    words.mapIndexed { index, word -> if (isPalindrome(word, 0, word.length-1)) index else null }.filterNotNull()

private fun isPalindrome(str: String, s: Int, e: Int): Boolean {
  var start = s
  var end = e
  if (start > end) {
    return false
  }
  while (start < end) {
    if (str[start] != str[end]) return false
    start++
    end--
  }
  return true
}

fun main(){
  val palindromePairs = PalindromePairs()
  listOf(
      arrayOf("abcd","dcba","lls","s","sssll")
  ).map {
    palindromePairs.execute(it)
  }
}

class a {
  private var root: Node? = null

  fun palindromePairs(words: Array<String>?): List<List<Int?>?>? {
    if (words == null || words.size == 0) return ArrayList()
    root = Node()
    val n = words.size
    val finalResult: MutableList<List<Int?>?> = ArrayList()
    for (i in 0 until n) {
      if (words[i].isEmpty()) {
        // Pair with all self-palindrome.
        val selfPalindromeWordIndices = getSelfPalindrome(words)
        for (pairId in selfPalindromeWordIndices) {
          finalResult.add(ArrayList(Arrays.asList(i, pairId)))
          finalResult.add(ArrayList(Arrays.asList(pairId, i)))
        }
      } else {
        insert(reverse(words[i]), i)
      }
    }
    for (i in 0 until n) {
      val wordIndices = search(words[i], i)
      for (pairId in wordIndices) {
        finalResult.add(ArrayList(Arrays.asList(i, pairId)))
      }
    }
    return finalResult
  }

  /****************** Trie-related  */
  private fun search(word: String, index: Int): List<Int> {
    val wordIndices: MutableList<Int> = ArrayList()
    var ptr = root
    val n = word.length
    for (i in 0 until n) {
      val label = word[i] - 'a'
      if (ptr!!.endWordId > -1 && isPalindrome(word, i, n - 1)) {
        wordIndices.add(ptr.endWordId)
      }
      if (ptr.children[label] == null) {
        return wordIndices
      }
      ptr = ptr.children[label]
    }
    if (ptr!!.endWordId > -1 && ptr.endWordId != index) wordIndices.add(ptr.endWordId)
    if (!ptr.belowPalindromeWordIds.isEmpty()) wordIndices.addAll(ptr.belowPalindromeWordIds)
    return wordIndices
  }

  private fun insert(word: String, index: Int) {
    var ptr = root
    val n = word.length
    for (i in 0 until n) {
      val label = word[i] - 'a'
      if (ptr!!.children[label] == null) ptr.children[label] = Node()
      ptr = ptr.children[label]
      if (isPalindrome(word, i + 1, n - 1)) ptr!!.belowPalindromeWordIds.add(index)
    }
    ptr!!.endWordId = index
  }

  internal class Node {
    var children: Array<Node?>
    var endWordId // Equals to -1 in default. If it is a word's end, it is the index of the word.
        : Int
    var belowPalindromeWordIds // List of word indices such that nodes below can construct a palindrome.
        : MutableList<Int>

    init {
      children = arrayOfNulls(26)
      endWordId = -1
      belowPalindromeWordIds = ArrayList()
    }
  }

  /****************** Utility  */
  private fun reverse(str: String): String {
    return StringBuilder(str).reverse().toString()
  }

  private fun isPalindrome(str: String, start: Int, end: Int): Boolean {
    var start = start
    var end = end
    if (start > end) {
      return false
    }
    while (start < end) {
      if (str[start] != str[end]) return false
      start++
      end--
    }
    return true
  }

  private fun getSelfPalindrome(words: Array<String>): List<Int> {
    val wordIndices: MutableList<Int> = ArrayList()
    for (i in words.indices) {
      if (isPalindrome(words[i], 0, words[i].length - 1)) {
        wordIndices.add(i)
      }
    }
    return wordIndices
  }
}