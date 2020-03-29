package io.array

class MoveZerosToEnd {
  fun execute(input: IntArray) = input.apply {
    var count = 0

    input.forEach { value ->
      if (value != 0) {
        input[count] = value
        count++
      }
    }
    (count until input.size).forEach { input[it] = 0 }
  }
}

fun main() {
  val moveZerosToEnd = MoveZerosToEnd()
  listOf(
      intArrayOf(1, 2, 0, 4, 3, 0, 0, 0, 5, 0) to listOf(1, 2, 4, 3, 5, 0, 0, 0, 0, 0)

  ).map { (input, value) ->
    val output = moveZerosToEnd.execute(input).toList()
    val isValid = output == value
    if (isValid)
      println("$output is valid")
    else
      println("Except $value but instead got $output")
  }

}