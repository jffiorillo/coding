@file:Suppress("MemberVisibilityCanBePrivate")

package io.recursion

import io.queue.Coordinates
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class NQueens {

  lateinit var leftDiagonal : BooleanArray
  lateinit var rightDiagonal : BooleanArray
  lateinit var columns : BooleanArray
  lateinit var rows : BooleanArray

  fun totalNQueens(nQueens :Int): Int {
    leftDiagonal = BooleanArray(nQueens*3){false}
    rightDiagonal = BooleanArray(nQueens*3){false}
    columns = BooleanArray(nQueens){false}
    rows = BooleanArray(nQueens){false}
    return execute(nQueens)
  }

  fun execute(
      nQueens: Int,
      queens: List<Coordinates> = emptyList(),
      size: Int = nQueens,
      founds: MutableSet<List<Coordinates>> = mutableSetOf()): Int = when (size) {
    queens.size -> if (!founds.fold(false) { acc, item -> acc || item.containsAll(queens) }) {
      founds.add(queens)
//          println(queens)
      1
    } else {
      0
    }
    else -> {
      val validPositions = generateValidPositions(queens, size)
      if (validPositions.isEmpty() || validPositions.size < nQueens) {
        0
      } else {
        validPositions.map { queen ->
          columns[queen.j]= true
          rows[queen.i] = true
          leftDiagonal[queen.leftDiagonalIndex(size)] = true
          rightDiagonal[queen.rightDiagonalIndex(size)] = true
          execute(nQueens - 1, queens + queen, size, founds).also {
            columns[queen.j] = false
            rows[queen.i] = false
            leftDiagonal[queen.leftDiagonalIndex(size)] = false
            rightDiagonal[queen.rightDiagonalIndex(size)] = false

          } }.reduce { acc, i -> acc + i }
      }
    }
  }

  fun Coordinates.leftDiagonalIndex(size:Int) = i - j + size-1
  fun Coordinates.rightDiagonalIndex(size:Int) = i + j + size-1

  fun isValid(newQueen: Coordinates, queens: List<Coordinates>, size: Int): Boolean =
      !columns[newQueen.j] && !rows[newQueen.i] && !leftDiagonal[newQueen.leftDiagonalIndex(size)] &&
          !rightDiagonal[newQueen.rightDiagonalIndex(size)]

  fun isValid1(newQueen: Coordinates, queens: List<Coordinates>, size: Int): Boolean =
      queens.fold(true) { acc, queen ->
        acc && newQueen.i != queen.i &&
            newQueen.j != queen.j
      } && generateDiagonals(newQueen, size).let { invalid -> queens.none { invalid.contains(it) } }

  fun generateDiagonals(newQueen: Coordinates, size: Int) = (1..min(newQueen.i, newQueen.j))
      .map { value -> Coordinates(newQueen.i - value, newQueen.j - value) } +
      (1 until (size - max(newQueen.i, newQueen.j)))
          .map { value -> Coordinates(newQueen.i + value, newQueen.j + value) } +
      ((1 until size).flatMap { value ->
        listOf(
            Coordinates(newQueen.i + value, newQueen.j - value),
            Coordinates(newQueen.i - value, newQueen.j + value))
      })
          .filter { it.j in 0 until size && it.i in 0 until size }

  fun generateValidPositions1(queens: List<Coordinates>, size: Int): List<Coordinates> =
      (0 until size).flatMap { i ->
        (0 until size).map { j ->
          Coordinates(i, j)
        }
      }.filter { isValid(it, queens, size) }

  fun generateValidPositions(queens: List<Coordinates>, size: Int): List<Coordinates> =
      (0 until size).flatMap { i ->
        if (queens.firstOrNull { it.i == i } == null) {
          val jValues = queens.map { it.j }
          (0 until size).flatMap { j ->
            if (j !in jValues) listOf(Coordinates(i, j)) else emptyList()
          }
        } else {
          emptyList()
        }
      }.filter { isValid(it, queens, size) }
}

@ExperimentalTime
fun main() {
  val nQueens = NQueens()
//  nQueens.isValid(Coordinates(3,0),listOf(Coordinates(0,3)),5).let{
//    println("${it}")
//  }
  val numbers = 5
  var acc = Duration.ZERO
  repeat(numbers) {
    measureTime { print(nQueens.totalNQueens(8)) }.let { time ->
      acc += time
      print("-$time ")
    }
  }
  println("\ntotal: $acc avg: ${acc / numbers}")
}