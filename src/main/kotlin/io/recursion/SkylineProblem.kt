package io.recursion

// https://leetcode.com/explore/learn/card/recursion-ii/507/beyond-recursion/3006/
class SkylineProblem {

  fun execute(buildings: Array<IntArray>): List<List<Int>> {
    val sortDots = convertToPoints(buildings)
    val currentArea = mutableListOf<Int>()
    val result: MutableList<List<Int>> = mutableListOf()
    var currentAreaMax = 0
    sortDots.forEach { element ->
      when (element.type) {
        BuildingPointType.BEGIN -> {
          currentArea.add(element.height)
          if (element.height > currentAreaMax) {
            currentAreaMax = element.height
            result.add(element.toList())
          }
        }
        BuildingPointType.END -> {
          currentArea.remove(element.height)
          val newCurrentAreaMax = currentArea.maxOrNull() ?: 0
          if (newCurrentAreaMax != currentAreaMax) {
            currentAreaMax = newCurrentAreaMax
            result.add(listOf(element.x, currentAreaMax))
          }
        }
      }
    }
    return result
  }

  fun convertToPoints(buildings: Array<IntArray>): List<BuildingPoint> =
      buildings.flatMap {
        listOf(BuildingPoint(it[0], it[2], BuildingPointType.BEGIN),
            BuildingPoint(it[1], it[2], BuildingPointType.END))
      }.sorted()

  data class BuildingPoint(val x: Int, val height: Int, val type: BuildingPointType) : Comparable<BuildingPoint> {
    fun toList() = listOf(x, height)
    private fun getComparableValue() = if (this.type == BuildingPointType.BEGIN) -this.height else this.height
    override fun compareTo(other: BuildingPoint): Int =
        when {
          this.x != other.x -> this.x - other.x
          else -> getComparableValue() - other.getComparableValue()
        }
  }

  enum class BuildingPointType {
    BEGIN, END
  }

}

fun main() {
  val skylineProblem = SkylineProblem()
  skylineProblem.convertToPoints(arrayOf(intArrayOf(0, 2, 3), intArrayOf(0, 1, 2), intArrayOf(3, 5, 3), intArrayOf(4, 5, 2))).map {
    println(it)
  }
}