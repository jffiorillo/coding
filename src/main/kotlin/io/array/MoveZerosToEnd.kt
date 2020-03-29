package io.array

class MoveZerosToEnd {
  fun execute(input: IntArray) = input.apply {
    var index = 0
    var firstZeroIndex = -1
    while (index < size) {
      if (this[index] == 0) {
        if (firstZeroIndex == -1) firstZeroIndex = index
        val nextNotZeroElement = nextNotZeroElement(input, index + 1)
        swapValues(input, firstZeroIndex, nextNotZeroElement)
        index = nextNotZeroElement

      } else if (firstZeroIndex != -1) {
        swapValues(input, firstZeroIndex, index)
      }
      if (firstZeroIndex != -1) firstZeroIndex++
      index++
    }
  }

  private fun swapValues(input: IntArray, firstZeroIndex: Int, nextNotZeroElement: Int) {
    if (nextNotZeroElement != input.size) {
      input[firstZeroIndex] = input[nextNotZeroElement]
      input[nextNotZeroElement] = 0
    }
  }

  private fun nextNotZeroElement(input: IntArray, index: Int): Int {
    var nextNotZeroElement = index
    while (nextNotZeroElement < input.size && input[nextNotZeroElement] == 0) {
      nextNotZeroElement++
    }
    return nextNotZeroElement
  }
}

fun main() {
  val moveZerosToEnd = MoveZerosToEnd()
  println(moveZerosToEnd.execute(intArrayOf(1, 2, 0, 4, 3, 0, 0, 0, 5, 0)).toList())

}