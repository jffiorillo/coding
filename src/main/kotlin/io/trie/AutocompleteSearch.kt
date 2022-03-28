package io.trie

// https://cheonhyangzhang.gitbooks.io/leetcode-solutions/642-design-search-autocomplete-system.html
class AutocompleteSearch(historicalData: List<Suggestion>) {

  val historicalData: TreeNode = TreeNode(Suggestion("")).apply {
    historicalData.forEach { (word, weight) -> addWord(word, weight) }
  }

  private var currentSearch: TreeNode = this.historicalData


  fun getSuggestions(char: Char): List<Suggestion> = when (char) {
    '#' -> {
      currentSearch.finishPhrase()
      updateSuggestions(historicalData, currentSearch.suggestion)
      currentSearch = historicalData
      emptyList()
    }
    else -> {
      currentSearch = currentSearch.getOrAdd(char)
      currentSearch.suggestions//.map { it.phrase }
    }
  }

  data class TreeNode(
      val suggestion: Suggestion,
      val children: MutableMap<Int, TreeNode> = mutableMapOf(),
      val suggestions: MutableList<Suggestion> = mutableListOf()) {
    fun addWord(word: String, times: Int) =
        word.fold(this) { acc, char -> acc.getOrAdd(char) }.also {
          it.suggestion.weight = it.suggestion.weight + times
          updateSuggestions(this, Suggestion(word, times))
        }

    fun getOrAdd(char: Char) = children[char.toInt()] ?: TreeNode(Suggestion(this.suggestion.phrase + char)).also { children[char.toInt()] = it }

    fun updateSuggestions(suggestion: Suggestion) = this.suggestions.updateSuggestions(suggestion)

    fun finishPhrase() {
      suggestion.weight++
    }
  }

  data class Suggestion(val phrase: String, var weight: Int=0) : Comparable<Suggestion> {
    override fun compareTo(other: Suggestion): Int = when {
      this.weight < other.weight -> 1
      this.weight == other.weight -> -this.phrase.compareTo(other.phrase)
      else -> -1
    }

  }
}

private fun updateSuggestions(root: AutocompleteSearch.TreeNode, suggestion: AutocompleteSearch.Suggestion) =
    suggestion.phrase.fold(root) { acc, char ->
      acc.children[char.toInt()]!!.also { it.updateSuggestions(suggestion) }
    }


private fun MutableList<AutocompleteSearch.Suggestion>.updateSuggestions(suggestion: AutocompleteSearch.Suggestion) =
    this.minByOrNull { it.weight }?.let { minSuggestion ->
      when {
        this.contains(suggestion) -> false.also { this.sort() }
        this.size < 3 -> this.add(suggestion).also { this.sort() }
        minSuggestion < suggestion -> {
          this.remove(minSuggestion)
          this.add(suggestion)
          this.sort()
        }
        else -> false
      }

    } ?: this.add(suggestion)


fun main() {
  val autocompleteSearch = AutocompleteSearch(listOf(
      AutocompleteSearch.Suggestion("i love you", 5), AutocompleteSearch.Suggestion("island", 3), AutocompleteSearch.Suggestion("ironman", 2),
      AutocompleteSearch.Suggestion("i love leetcode", 2)
  ))

  println("You should see how 'i l#' grows and go before i love leetcode because the user uses it")
  listOf("i l#", "i l#", "i l#", "i #").forEachIndexed { index, phrase ->
    phrase.forEach { char ->
      println("$index, $phrase $char ${autocompleteSearch.getSuggestions(char)}")
    }
    println("---")
  }
}
