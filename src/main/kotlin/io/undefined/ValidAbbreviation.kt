package io.undefined

// https://leetcode.com/problems/valid-word-abbreviation/
class ValidAbbreviation {

  fun execute(word: String, abbr: String): Boolean {
    if (word.length < abbr.length) return false
    var wordIndex = 0
    var abbrIndex = 0
    loop@ while (wordIndex < word.length && abbrIndex < abbr.length) {
      val char = abbr[abbrIndex]
      when {
        char.isDigit() -> {
          var number = Character.getNumericValue(char)
          if (number == 0) return false
          while (abbrIndex + 1 < abbr.length && abbr[abbrIndex + 1].isDigit()) {
            abbrIndex++
            number = 10 * number + Character.getNumericValue(abbr[abbrIndex])
          }
          wordIndex += number
          abbrIndex++
        }
        char != word[wordIndex] -> return false
        else -> {
          wordIndex++
          abbrIndex++
        }
      }
    }
    return wordIndex == word.length && abbrIndex == abbr.length
  }
}