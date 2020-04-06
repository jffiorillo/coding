package io.array

import io.utils.runTests

// https://leetcode.com/problems/group-anagrams/
class GroupAnagrams {

  fun execute(input: Array<String>): List<List<String>> =
      input.map { word -> (word to word.toSet().map { char -> char to word.count { it == char } }.toMap()) }
          .groupBy { it.second }.values.map { pairWordList -> pairWordList.map { elem -> elem.first } }

}

fun main() {
  runTests(listOf(
      arrayOf("eat", "tea", "tan", "ate", "nat", "bat") to listOf(listOf("eat", "tea", "ate"), listOf("tan", "nat"), listOf("bat")),
      arrayOf("hos", "boo", "nay", "deb", "wow", "bop", "bob", "brr", "hey", "rye", "eve", "elf", "pup", "bum", "iva", "lyx", "yap", "ugh", "hem", "rod", "aha", "nam", "gap", "yea", "doc", "pen", "job", "dis", "max", "oho", "jed", "lye", "ram", "pup", "qua", "ugh", "mir", "nap", "deb", "hog", "let", "gym", "bye", "lon", "aft", "eel", "sol", "jab")
          to listOf(
          listOf("sol"),
          listOf("wow"),
          listOf("gap"),
          listOf("hem"),
          listOf("yap"),
          listOf("bum"),
          listOf("ugh", "ugh"),
          listOf("aha"),
          listOf("jab"),
          listOf("eve"),
          listOf("bop"),
          listOf("lyx"),
          listOf("jed"),
          listOf("iva"),
          listOf("rod"),
          listOf("boo"),
          listOf("brr"),
          listOf("hog"),
          listOf("nay"),
          listOf("mir"),
          listOf("deb", "deb"),
          listOf("aft"),
          listOf("dis"),
          listOf("yea"),
          listOf("hos"),
          listOf("rye"),
          listOf("hey"),
          listOf("doc"),
          listOf("bob"),
          listOf("eel"),
          listOf("pen"),
          listOf("job"),
          listOf("max"),
          listOf("oho"),
          listOf("lye"),
          listOf("ram"),
          listOf("nap"),
          listOf("elf"),
          listOf("qua"),
          listOf("pup", "pup"),
          listOf("let"),
          listOf("gym"),
          listOf("nam"),
          listOf("bye"),
          listOf("lon")
      )
  )) { (input, value) -> value to GroupAnagrams().execute(input) }
}