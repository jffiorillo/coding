package io.recursion

class Fibonacci {

  private val defaultValue = Int.MAX_VALUE

  fun execute(input: Int, memoization: HashMap<Int, Int> = hashMapOf<Int, Int>().apply {
    (2..input).map {
      this[it] = defaultValue
    }
  }): Int =
      when {
        input == 0 || input == 1 -> input
        memoization[input] != defaultValue -> memoization[input]!!
        else -> {
          (execute(input - 1) + execute(input - 2)).also {
            memoization[input] = it
          }
        }
      }

}

fun main(){
  val fibonacci = Fibonacci()
  fibonacci.execute(5)
}