package io

//http://blog.gainlo.co/index.php/2016/10/07/facebook-interview-longest-substring-without-repeating-characters/

class LongestSubstringWithoutRepeatingCharacters {

  fun execute(input: String): String {
    val hashSet = HashSet<Char>()
    var begin = 0
    var biggestSubstring = ""
    for ((index, value) in input.withIndex()) {
      if (hashSet.contains(value)) {
        input.substring(begin, index).let { substring ->
          if (substring.length > biggestSubstring.length) {
            biggestSubstring = substring
          }
          //Remove all character from the beginning until we found the duplicated character
          while (begin < input.length - 1) {
            val current = input[begin]
            hashSet.remove(current)
            begin++
            if (current == value) {
              break
            }
          }
        }
      } else {
        hashSet.add(value)
      }
    }
    return if (begin != input.length - 1 && input.substring(begin).length > biggestSubstring.length)
      input.substring(begin)
    else biggestSubstring
  }
}

fun main() {
  LongestSubstringWithoutRepeatingCharacters().let { action ->
    listOf("abccdefgh", "abcadbef", "abac", "aaaaaa","abcdefghijklmnocqrt").forEach { value ->
      val execute = action.execute(value)
      println("Example: '$value' output: $execute size ${execute.length}")
    }
  }
}