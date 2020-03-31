package io.queue

import java.util.*

// https://leetcode.com/problems/tag-validator/
class TagValidator {

  private val tagRegex = "<[/|A-Z]+>".toRegex()
  private val cDataRegex = "<!\\[CDATA\\[.*]]>".toRegex()

  fun isValid(code: String): Boolean = execute(code)

  fun execute(input: String): Boolean {
    when {
      input.length < 5 || input.first() != '<' || input[1] == '!' -> return false
    }

    val stack = LinkedList<String>()
    var index = 0
    while (index < input.length) {
      when (input[index]) {
        '<' -> {
          if (index + 1 == input.length)
            return false
          when (input[index + 1]) {
            ' ' -> {
              stack.push(input[index].toString())
              index += 2
            }
            '!' -> {
              val lastCDataContentIndex = input.lastCDataContextIndex(index)
              val cData = input.substring(index, lastCDataContentIndex)
              if (!cData.isCDataValid()) return false
              index = lastCDataContentIndex
            }
            else -> {
              val lastTagIndex = input.lastTagIndex(index)
              val tag = input.substring(index, lastTagIndex)
              when {
                tag.isTagInvalid()
                    || tag.isClosingTag() && (stack.isEmpty() || stack.pop().areInvalidTags(tag))-> return false
                tag.isOpenTag() -> stack.push(tag)
              }
              index = lastTagIndex
            }
          }
        }
        '>' ->  {
          when{
            stack.isNotEmpty() && stack.peek() == "<" -> {
              stack.pop()
            }
          }
          index++
        }
        else -> {
          index = input.moveUntilNextValue(index)
        }
      }
    }
    return stack.isEmpty()
  }

  private fun String.moveUntilNextValue(currentIndex:Int):Int{
    var index = currentIndex
    loop@ while(index < length){
      when (this[index]){
        '<', '>' -> break@loop
        else -> index++
      }
    }
    return index
  }

  private fun String.lastTagIndex(index: Int): Int {
    var currentIndex = index
    while (currentIndex < length && this[currentIndex] != '>') currentIndex++
    return if (currentIndex == length) currentIndex else currentIndex+1
  }

  private fun String.isTagValid() = length < 13 && tagRegex.matchEntire(this) != null
  private fun String.isTagInvalid() = !isTagValid()
  private fun String.isClosingTag() = this[1] == '/'
  private fun String.isOpenTag() = !isClosingTag()

  private fun String.areValidTags(closingTag: String) = this.length + 1 == closingTag.length &&
      this.substring(1, this.length - 1) == closingTag.substring(2, closingTag.length-1)

  private fun String.areInvalidTags(closingTag: String) = !areValidTags(closingTag)

  private fun String.isCDataValid() = cDataRegex.matchEntire(this) != null

  private fun String.lastCDataContextIndex(startIndex: Int): Int {
    var index = startIndex
    while (index + 3 < length && this.substring(index, index + 3) != "]]>") index++
    return index + 3
  }
}

fun main(){

  val tagValidator = TagValidator()
  listOf(
      "<DIV>This is the first line <![CDATA[<div>]]></DIV>" to true,
      "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>" to true,
      "<A>  <B> </A>   </B>" to false,
      "<DIV>  div tag is not closed  <DIV>" to false,
      "<DIV>  unmatched <  </DIV>" to false,
      "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>" to false,
      "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>" to false,
      "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>" to false,
      "<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>" to false,
      "<TRUE><![CDATA[wahaha]]]><![CDATA[]> wahaha]]></TRUE>" to true
  ).forEachIndexed { index, (input,value) ->
    val output = tagValidator.execute(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}