package io.array

import java.util.*


// https://leetcode.com/problems/course-schedule-iii/solution/
class CourseScheduleSolution {

  fun execute(courses: Array<IntArray>): Int {
    courses.sortBy { it.endDay() }
    var count = 0
    var time = 0
    courses.map { it.duration() to it.endDay() }.forEachIndexed { index, (duration, endDay) ->
      if (duration + time <= endDay) {
        count++
        time += duration
      } else {
        var maxRow = index
        (0 until index).forEach { j ->
          if (courses[j].duration() > courses[maxRow].duration()) maxRow = j
        }
        if (courses[maxRow].duration() > duration) {
          time += duration - courses[maxRow].duration()
        }
        courses[maxRow][0] = -1
      }
    }
    return count
  }

  private fun IntArray.duration() = this.first()
  private fun IntArray.endDay() = this[1]
}