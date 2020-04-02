package io.array


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
        tryToChangePreviousLongerCourse(courses, index, duration)?.let { time += it }
      }
    }
    return count
  }

  private fun tryToChangePreviousLongerCourse(courses: Array<IntArray>, index: Int, duration: Int): Int? {
    var longestCourse = index
    (0 until index).forEach { j ->
      if (courses[j].duration() > courses[longestCourse].duration())
        longestCourse = j
    }
    return courses[longestCourse].duration().let { longestDuration ->
      courses[longestCourse][0] = -1
      if (longestDuration > duration) (duration - longestDuration) else null
    }
  }

  private fun IntArray.duration() = this.first()
  private fun IntArray.endDay() = this[1]
}