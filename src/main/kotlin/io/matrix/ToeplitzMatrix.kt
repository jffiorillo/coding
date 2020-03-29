package io.matrix

// https://leetcode.com/problems/toeplitz-matrix/
class ToeplitzMatrix {

  fun execute(matrix: Array<IntArray>): Boolean {
    var row = matrix.size-2
    while(row > 0){
      var col = 1
      var tempRow = row + 1
      val value = matrix[row][0]
      while (tempRow < matrix.size && col < matrix[row].size) {
        if (matrix[tempRow][col] != value) {
          return false
        }
        col++
        tempRow++
      }
      row--
    }
    matrix[0].indices.forEach { col ->
      var row = 1
      var tempCol = col + 1
      val value = matrix[0][col]
      while (tempCol < matrix[0].size && row < matrix.size) {
        if (matrix[row][tempCol] != value) {
          return false
        }
        tempCol++
        row++
      }

    }
    return true
  }
}

fun main() {
  val toeplitzMatrix = ToeplitzMatrix()
  listOf(
      arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 1, 2, 3), intArrayOf(9, 5, 1, 2)) to true

//      arrayOf(
//          intArrayOf(36, 59, 71, 15, 26, 82, 87),
//          intArrayOf(56, 36, 59, 71, 15, 26, 82),
//          intArrayOf(15, 0, 36, 59, 71, 15, 26)) to false
  ).forEachIndexed { index, (input, value) ->
    val output = toeplitzMatrix.execute(input)
    val isValid = output == value
    if (isValid)
      println("index $index $output is valid")
    else
      println("index $index Except $value but instead got $output")
  }
}