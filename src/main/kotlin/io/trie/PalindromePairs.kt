package io.trie

// https://cheonhyangzhang.gitbooks.io/leetcode-solutions/303-range-sum-query---immutable/336-palindrome-pairs.html
class PalindromePairs {

  fun execute(words: Array<String>): List<Pair<Int, Int>> =
      words.mapIndexed { index, word -> Element(word, index) }.fold(mutableListOf()) { acc, element ->
        acc.apply {
          addAll((element.nextIndex until words.size).map { Element(words[it], it) }
              .flatMap { nextElement ->
                listOf(element to nextElement, nextElement to element)
                    .filter { (it.first.word + it.second.word).isPalidrome() }.map { it.first.index to it.second.index }
              })
        }
      }


  data class Element(val word: String, val index: Int) {
    val nextIndex get() = index + 1
  }
}

fun String.isPalidrome() = this.reversed() == this

fun main() {
  val palindromePairs = PalindromePairs()
  listOf(
      arrayOf("abcd", "dcba", "lls", "s", "sssll")
  ).map {
    println(palindromePairs.execute(it))
  }
}