package io.permutation

//https://leetcode.com/explore/learn/card/recursion-ii/507/beyond-recursion/2903/
class Permutations {

  fun execute(input: List<Int>): List<MutableList<Int>> = when {
    input.isEmpty() -> emptyList()
    input.size == 1 -> listOf(mutableListOf(input.first()))
    else -> {
      input.flatMap { number ->
        execute(input - number).map { item -> item.also { it.add(0, number) } }
      }
    }
  }
}

fun main(){
  val permutations = Permutations()

  permutations.execute(listOf(1,2,3)).map { println(it) }
}