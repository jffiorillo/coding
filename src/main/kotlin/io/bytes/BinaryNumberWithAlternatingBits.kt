package io.bytes

class BinaryNumberWithAlternatingBits {
  fun execute(input: Int): Boolean {
    if (input == 0) return false
    var current = input /2
    var rem = input.rem(2)
    while(current > 0){
      if (rem == current.rem(2))
        return false
      rem = current.rem(2)
      current /= 2
    }
    return true
  }
}

fun main() {
  val binaryNumberWithAlternatingBits = BinaryNumberWithAlternatingBits()
  listOf(
      7 to false
  ).forEachIndexed { index, (input, value) ->
    val output = binaryNumberWithAlternatingBits.execute(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}