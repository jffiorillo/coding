package io.recursion

enum class Tower(val title: String) {
  LEFT("left"),
  MIDDLE("middle"),
  RIGHT("right")
}

fun moveDisk(source: Tower, destination: Tower) =
    println("Move ${source.title}'s top disk to ${destination.title}")

fun solveHanoi(size: Int, source: Tower, destination: Tower) {
  if (size > 0) {
    val tmp = (Tower.values().toList() - source - destination).first()
    solveHanoi(size - 1, source, tmp)
    moveDisk(source, destination)
    solveHanoi(size - 1, tmp, destination)
  }
}

fun solveHanoi(size: Int,
               source: HanoiTower.Tower,
               buffer: HanoiTower.Tower,
               destination: HanoiTower.Tower,
               result: MutableList<String>) {
  if (size > 0) {
    solveHanoi(size - 1, source, destination, buffer, result)
    source.moveToTop(destination, result)
    solveHanoi(size - 1, buffer, source, destination, result)
  }
}

fun main() {
  val n = 3
//  solveHanoi(n, Tower.LEFT, Tower.RIGHT)
  val tower0 = HanoiTower.Tower("A")
  val tower1 = HanoiTower.Tower("B")
  val tower2 = HanoiTower.Tower("C")
  (n downTo 1).forEach { tower0.add(it) }
  val result = mutableListOf<String>()
  solveHanoi(n, tower0, tower1, tower2, result)
  println(tower0)
  println(tower1)
  println(tower2)
  result.forEach { println(it) }
}