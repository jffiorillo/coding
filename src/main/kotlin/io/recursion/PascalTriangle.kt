package io.recursion


// https://leetcode.com/explore/featured/card/recursion-i/251/scenario-i-recurrence-relation/3234/
class PascalTriangle {

  fun executeRecursive(index: Int, result: MutableList<Int> = MutableList(index + 1) { 1 }): MutableList<Int> = when (index) {
    0, 1 -> result
    else -> {
      executeRecursive(index - 1, result).also {
        var temp = result[1]
        result[1] = temp + 1
        (2 until index).map { i ->
          result[i].let { newTemp ->
            result[i] = temp + newTemp
            temp = newTemp
          }
        }
      }
    }
  }

  fun executeIterative(index: Int) = MutableList(index + 1) { 1 }.apply {
    (1 until index).map { x ->
      var temp = this[1]
      this[1] = temp + 1
      (2 until x + 1).map { index ->
        this[index].let { newTemp ->
          this[index] = temp + newTemp
          temp = newTemp
        }
      }
    }
  }
}


fun main() {
  val pascalTriangle = PascalTriangle()
  println("${pascalTriangle.executeIterative(5)}")
  println("${pascalTriangle.executeRecursive(5)}")
  println("${pascalTriangle.executeRecursive(5) == pascalTriangle.executeIterative(5)}")
}