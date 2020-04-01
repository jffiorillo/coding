package io.matrix

// https://leetcode.com/problems/flipping-an-image/
class FlippingAnImage {

  fun execute(input: Array<IntArray>): Array<IntArray> = input.also {
    input.forEachIndexed { indexRow, row ->
      val lastIndex = row.size - 1
      (lastIndex downTo row.size / 2).forEach { index ->
        val value = row[index]
        val oppositeIndex = lastIndex - index
        input[indexRow][index] = row[oppositeIndex].swap()
        input[indexRow][oppositeIndex] = value.swap()
      }
    }
  }

  private fun Int.swap() = if (this == 0) 1 else 0
}


fun main() {
  val flippingAnImage = FlippingAnImage()
  listOf(
      arrayOf(
          intArrayOf(1, 1, 0),
          intArrayOf(1, 0, 1),
          intArrayOf(0, 0, 0)
      ) to
          listOf(
              listOf(1, 0, 0),
              listOf(0, 1, 0),
              listOf(1, 1, 1)
          )
  ).forEachIndexed { index, (input, value) ->
    val output = flippingAnImage.execute(input).map { it.toList() }.toList()
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}
