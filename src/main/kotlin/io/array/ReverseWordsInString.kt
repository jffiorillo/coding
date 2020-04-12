package io.array

import io.utils.runTests
import kotlin.math.absoluteValue


// https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164/
class ReverseWordsInString {

  fun execute(phrase: String): String {
    val input = generateStringBuilderWithoutExtraSpaces(phrase)
    var startFirst = 0
    var endSecond = input.length - 1
    while (startFirst < endSecond) {
      val endFirst = findNextSpaceWord(input, startFirst) { it + 1 }.second - 1
      val startSecond = findNextSpaceWord(input, endSecond) { it - 1 }.second + 1
      if (startSecond <= startFirst)
        break
      val firstWordRange = startFirst..endFirst
      val firstWordSize = endFirst - startFirst + 1
      val secondWordSize = endSecond - startSecond + 1
      val sizeDif = (firstWordSize - secondWordSize).absoluteValue
      var secondWord: String
      if (secondWordSize > firstWordSize) {
        secondWord = moveFirstToSecond(input, startFirst, endFirst, startSecond, endSecond)
        input.changeToSpace(firstWordRange)
        moveDiffForward(input, sizeDif, endFirst, startSecond)
      } else {
        val firstWord = input.substring(firstWordRange)
        val secondWordRange = startSecond..endSecond
        secondWord = input.substring(secondWordRange)
        if (sizeDif > 0) moveDiffBackwards(input, sizeDif, endFirst, startSecond)
        input.changeToSpace(secondWordRange)
        (firstWord.length - 1 downTo 0).forEach {
          input[endSecond - (firstWord.length - it - 1)] = firstWord[it]
        }
      }
      secondWord.forEachIndexed { index, char -> input[startFirst + index] = char }
      startFirst += secondWordSize + 1
      endSecond -= firstWordSize + 1
    }
    return input.toString()
  }

  private fun moveDiffBackwards(input: StringBuilder, sizeDif: Int, endFirst: Int, startSecond: Int) {
    (endFirst + 1 until startSecond).forEach { input[it - sizeDif] = input[it] }
  }

  private fun moveFirstToSecond(input: StringBuilder, startFirst: Int, endFirst: Int, startSecond: Int, endSecond: Int): String {
    val secondWordRange = startSecond..endSecond
    val second = input.substring(secondWordRange)
    input.changeToSpace(secondWordRange)
    (endFirst downTo startFirst).forEach {
      input[endSecond - (endFirst - it)] = input[it]
    }
    return second
  }

  private fun moveDiffForward(input: StringBuilder, sizeDif: Int, endFirst: Int, startSecond: Int) {
    (startSecond - 1 downTo endFirst).forEach { input[it + sizeDif] = input[it] }
  }

  private fun StringBuilder.changeToSpace(range: IntRange) = range.forEach { this[it] = ' ' }

  private fun findNextSpaceWord(input: CharSequence, start: Int, action: (Int) -> Int): Pair<Int, Int> {
    var index = start
    while (index in input.indices && input[index] == ' ') index = action(index)
    val startWord = index
    while (index in input.indices && input[index] != ' ') index = action(index)
    return startWord to index
  }

  private fun generateStringBuilderWithoutExtraSpaces(input: String): StringBuilder {
    val result = StringBuilder()
    var current = 0
    while (current < input.length) {
      val (start, end) = findNextSpaceWord(input, current) { it + 1 }
      (start until end).forEach { result.append(input[it]) }
      if (end != input.length && (end until input.length).fold(false) { acc, index -> acc || input[index] != ' ' }) {
        result.append(' ')
      }
      current = end
    }
    return result
  }
}

fun main() {
  runTests(listOf(
      "The sky is  blue" to "blue is sky The",
      "  hello world!  " to "world! hello",
      "a good   example" to "example good a",
      "a, yqo! qjktum ym. .fumuhau" to ".fumuhau ym. qjktum yqo! a,"
  )) { (input, value) -> value to ReverseWordsInString().execute(input) }
}