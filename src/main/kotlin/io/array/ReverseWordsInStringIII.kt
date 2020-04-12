package io.array

import io.utils.runTests

// https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1165/
class ReverseWordsInStringIII {
  fun execute(phrase: String): String {
    val input = phrase.fold(StringBuilder()) { acc, char -> acc.append(char) }
    var index = 0
    while (index < input.length) {
      var (start, end) = getWordIndex(input, index)
      index = end + 1
      while (start < end) {
        input[start].let { temp ->
          input[start] = input[end]
          input[end] = temp
        }
        start++
        end--
      }
    }
    return input.toString()
  }

  private fun getWordIndex(input: CharSequence, start: Int): Pair<Int, Int> {
    var index = start
    while (index < input.length && input[index] == ' ') index++
    val startWord = index
    while (index < input.length && input[index] != ' ') index++
    return startWord to index - 1
  }
}

fun main(){
  runTests(listOf(
      "Let's take LeetCode contest" to "s'teL ekat edoCteeL tsetnoc"
  )){(input,value) -> value to ReverseWordsInStringIII().execute(input)}
}
